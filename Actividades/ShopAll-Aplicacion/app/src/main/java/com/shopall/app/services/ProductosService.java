package com.shopall.app.services;

import com.shopall.app.exceptions.BusinessException;
import com.shopall.app.models.dto.ProductoDTO;
import com.shopall.app.models.entity.Categoria;
import com.shopall.app.models.entity.Producto;
import com.shopall.app.models.entity.Response;
import com.shopall.app.models.entity.TiendaVendedor;
import com.shopall.app.repository.ICategoriaRepository;
import com.shopall.app.repository.IProductosRepository;
import com.shopall.app.repository.ITiendaVendedorRepository;
import com.shopall.app.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductosService implements IProductosService{

    @Autowired
    private IProductosRepository productoRepository;

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Autowired
    private ITiendaVendedorRepository tiendaVendedor;

    @Override
    public Response<ProductoDTO> guardarProducto(ProductoDTO productoDto) {
        Producto producto= new Producto();
        producto.setDetallesProducto(productoDto.getDetallesProducto());
        producto.setPrecio(productoDto.getPrecio());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setNombre(productoDto.getNombre());
        producto.setCategoria(buscarCategoriaPorId(productoDto.getIdCategoria()));
        producto.setTiendaVendedor(buscarVendedorPorId(productoDto.getIdVendedor()));

        producto= productoRepository.save(producto);

        productoDto.setIdProducto(producto.getIdProducto());

        Response<ProductoDTO> response= new Response<>();
        response.setData(productoDto);
        response.setCode(HttpStatus.OK.value());
        response.setSuccess(true);
        response.setMessage(Constantes.MENSAJE_REGISTRO_PRODUCTO + productoDto.getIdProducto());

        return response;
    }

    private TiendaVendedor buscarVendedorPorId(Integer id){
        Optional<TiendaVendedor> vendedor= tiendaVendedor.findById(id);
        if(vendedor.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND, Constantes.VENDEDOR_NO_EXISTENTE);
        }
        return  vendedor.get();
    }

    private Categoria buscarCategoriaPorId(Integer id){
            Optional<Categoria> cateroria= categoriaRepository.findById(id);
            if(cateroria.isEmpty()){
                throw new BusinessException(HttpStatus.NOT_FOUND, Constantes.CATEGORIA_NO_EXISTENTE);
            }
            return cateroria.get();
    }
}
