package com.shopall.app.services;

import java.util.Date;
import java.util.Optional;

import com.shopall.app.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shopall.app.exceptions.BusinessException;
import com.shopall.app.models.dto.CompraDTO;
import com.shopall.app.models.dto.ProductoAddDTO;
import com.shopall.app.models.entity.Compra;
import com.shopall.app.models.entity.FormasPago;
import com.shopall.app.models.entity.Inventario;
import com.shopall.app.models.entity.ListaCompras;
import com.shopall.app.models.entity.ListaComprasId;
import com.shopall.app.models.entity.Producto;
import com.shopall.app.models.entity.Response;
import com.shopall.app.models.entity.Usuario;
import com.shopall.app.repository.ICompraRepository;
import com.shopall.app.repository.IFormaPagoRepository;
import com.shopall.app.repository.IInvetnatarioRepository;
import com.shopall.app.repository.IListaComprasRepository;
import com.shopall.app.repository.IProductosRepository;
import com.shopall.app.repository.IUsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class ComprasService implements IComprasService{
	
	@Autowired
	private ICompraRepository compraRepository;
	
	@Autowired
	private IProductosRepository productosRepository;
	
	@Autowired 
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IFormaPagoRepository formaPagoRepository;
	
	@Autowired
	private IInvetnatarioRepository invetnatarioRepository;
	
	@Autowired
	private IListaComprasRepository listaComprasRepository;

	@Override
	@Transactional
	public Response<CompraDTO> generarCompra(CompraDTO compraDto) {

		//Se crea un objeto de compra.
		Compra compraHeader = new Compra();
		//Setea fecha.
		compraHeader.setFecha(new Date());
		//Seteando cantidad de productos.
		compraHeader.setCantidadProductos(compraDto.getProductos().size());

		//guardamos el usuario, primero vamos a la BD por el usuario y luego lo asignamos a la compra
        Usuario usuarioCompra = usuarioRepository.findById(compraDto.getIdUsuario())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, Constantes.USUARIO_NO_EXISTENTE));
        compraHeader.setIdUsuario(usuarioCompra);

        //Buscamos la forma de pago y se la asignamos a la entidad de la compra

        //FormasPago formasPago = formaPagoRepository.getReferenceById(compraDto.getIdFormaPago());
        FormasPago formasPago = formaPagoRepository.findById(compraDto.getIdFormaPago())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, Constantes.FORMA_PAGO_NO_EXISTENTE));
        compraHeader.setIdFormaPago(formasPago);

        //Calculamos el total recorriendo cada producto, obteniendo su precio unitario y multiplicandolo por la cantidad
        double totalCompra = 0.0;


        for(ProductoAddDTO productoJson : compraDto.getProductos()){
            //Por cada id encontrado en el json vamos a la BD por el objeto producto para hjacer los calculos
            Producto productoBD = productosRepository.findById(productoJson.getIdProducto())
                    .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "El producto con id "+productoJson.getIdProducto()+" No existe."));
            totalCompra += productoBD.getPrecio() * productoJson.getCantidad();
            //Actualizamos la informacion en el json tambien, para mantener al usuario retroalimientado
            productoJson.setPrecioUnitario(productoBD.getPrecio());
            productoJson.setTotal(productoBD.getPrecio() * productoJson.getCantidad());
        }

		//Verificamos la cantidad del inventario
		for(ProductoAddDTO productoJson : compraDto.getProductos()){

			Optional<Inventario> inventarioOptinal = invetnatarioRepository.findByTblTiendaVendedorByIdVendedorIdVendedorAndTblProductosByIdProductoIdProducto(compraDto.getIdVendedor(), productoJson.getIdProducto());
			Inventario inventarioStok=null;
			if(inventarioOptinal.isEmpty()) {
				throw new BusinessException(HttpStatus.NOT_FOUND, Constantes.INVENTARIO_NO_EXISTENTE_VENDEDOR );
			}
			inventarioStok= inventarioOptinal.get();
			if(inventarioStok.getStock()<productoJson.getCantidad()) {
				throw new BusinessException(HttpStatus.NOT_FOUND, "El producto con id "+productoJson.getIdProducto()+" No tiene stok suficiente");
			}
			inventarioStok.setStock(inventarioStok.getStock()-productoJson.getCantidad());
			invetnatarioRepository.save(inventarioStok);
		}

        //asignamos el total a la compra que se guardara en la BD
        compraHeader.setTotal(totalCompra);
        //Guardamos la compra para despues guardar la tabla de productos_compra
        compraRepository.save(compraHeader);

        //Una vez guardada la compra procedemos a guardar los productos en la tabla cruzada
        for(ProductoAddDTO productoJson : compraDto.getProductos()){
            //Traemos el producto de la bd
            Producto productoBd = productosRepository.findById(productoJson.getIdProducto())
                    .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "El producto con id "+productoJson.getIdProducto()+" No existe."));
            //Creamos la entidad que guardara en la BD
            ListaCompras productosCompra = new ListaCompras();
            //Guardamos el id compuesto cvreando una nueva entidad que representa ese ID compuesto
            ListaComprasId id = new ListaComprasId();
            id.setIdCompra(compraHeader.getIdCompra());
            id.setIdProducto(productoBd.getIdProducto());
            productosCompra.setId(id);
            //guardamos la entidad producto y la entidad compra para que se relacione
            productosCompra.setCompra(compraHeader);
            productosCompra.setProducto(productoBd);
            //Guardamos los datros calculados
            productosCompra.setCantidad(productoJson.getCantidad());
            productosCompra.setPrecioUnitario(productoJson.getPrecioUnitario());
            productosCompra.setTotal(productoBd.getPrecio() * productoJson.getCantidad());
            // Guardar la relación ProductosCompra en la base de datos
            listaComprasRepository.save(productosCompra);
        }


        //Asignamos los valores al dtro para retroalimentar al usuario en al compra
        compraDto.setIdCompra(compraHeader.getIdCompra());
        compraDto.setTotal(compraHeader.getTotal());
        compraDto.setCantidadProductos(compraHeader.getCantidadProductos());
        compraDto.setFecha(compraHeader.getFecha());

        Response<CompraDTO> response = new Response<>();
        response.setData(compraDto);
        response.setMessage(Constantes.MENSAJE_EXITO_COMPRA);
        response.setCode(HttpStatus.OK.value());
        response.setSuccess(true);
        return  response;
	}
}
