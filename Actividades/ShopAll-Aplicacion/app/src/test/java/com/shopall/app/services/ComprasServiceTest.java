package com.shopall.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.shopall.app.exceptions.BusinessException;
import com.shopall.app.models.dto.CompraDTO;
import com.shopall.app.models.dto.ProductoAddDTO;
import com.shopall.app.models.entity.FormasPago;
import com.shopall.app.models.entity.Inventario;
import com.shopall.app.models.entity.Producto;
import com.shopall.app.models.entity.Response;
import com.shopall.app.models.entity.Usuario;
import com.shopall.app.repository.ICompraRepository;
import com.shopall.app.repository.IFormaPagoRepository;
import com.shopall.app.repository.IInvetnatarioRepository;
import com.shopall.app.repository.IListaComprasRepository;
import com.shopall.app.repository.IProductosRepository;
import com.shopall.app.repository.IUsuarioRepository;
import com.shopall.app.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class ComprasServiceTest {

	@InjectMocks
	private ComprasService comprasService;
	
	@Mock
	private ICompraRepository compraRepository;
	
	@Mock
	private IProductosRepository productosRepository;
	
	@Mock 
	private IUsuarioRepository usuarioRepository;
	
	@Mock
	private IFormaPagoRepository formaPagoRepository;
	
	@Mock
	private IInvetnatarioRepository invetnatarioRepository;
	
	@Mock
	private IListaComprasRepository listaComprasRepository;
	
	private CompraDTO compraDTO;
	
	private Usuario usuario;
	private FormasPago formasPago;
	private List<ProductoAddDTO> listaProductos;
	private ProductoAddDTO producto; 
	private Producto producto2;
	private Inventario inventario;
	
	@BeforeEach
	void setUp() {
		//Producto que sera agregado a la lista
		producto= new ProductoAddDTO();
		producto.setIdProducto(1);
		
		listaProductos = new ArrayList<>();
		listaProductos.add(producto);
		
		compraDTO= new CompraDTO();
		
		compraDTO.setCantidadProductos(1);
		compraDTO.setFecha(new Date());
		compraDTO.setIdCompra(null);
		compraDTO.setIdFormaPago(1);
		compraDTO.setIdUsuario(1);
		compraDTO.setIdVendedor(1);
		compraDTO.setProductos(listaProductos);
		compraDTO.setTotal(null);
		compraDTO.getCantidadProductos();
		compraDTO.getFecha();
		compraDTO.getIdCompra();
		compraDTO.getIdFormaPago();
		compraDTO.getIdUsuario();
		compraDTO.getIdVendedor();
		compraDTO.getProductos();
		compraDTO.getTotal();
	
		usuario= new Usuario();
		
		formasPago= new FormasPago();
		
		producto2= new Producto();
		producto2.setIdProducto(1);
		producto2.setPrecio(10);
		producto2.setNombre("Laptop");
		producto2.setTiendaVendedor(null);
		producto2.getIdProducto();
		producto2.getPrecio();
		producto2.getNombre();
		producto2.getTiendaVendedor();
		
		inventario= new Inventario();
		inventario.setStock(10);
	}
	
	@Test
	void generarCompraExeption1Test() {
		 //Mandamos a probar la exception
    	BusinessException thrown = assertThrows(
    			BusinessException.class,
                () -> comprasService.generarCompra(compraDTO),
                Constantes.USUARIO_NO_EXISTENTE
        );
    	
    	assertTrue(thrown.getMensaje().contains("El usuario no existe."));

	}
	
	
	@Test
	void generarCompraExeption2Test() {
		when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		 //Mandamos a probar la exception
    	BusinessException thrown = assertThrows(
    			BusinessException.class,
                () -> comprasService.generarCompra(compraDTO),
                Constantes.FORMA_PAGO_NO_EXISTENTE
        );
    	
    	assertTrue(thrown.getMensaje().contains("La forma de pago no existe."));

	}
	
	@Test
	void generarCompraExeption3Test() {
		when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		when(formaPagoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(formasPago));
		 //Mandamos a probar la exception
    	BusinessException thrown = assertThrows(
    			BusinessException.class,
                () -> comprasService.generarCompra(compraDTO)
        );
    	
    	assertTrue(thrown.getMensaje().contains("El producto con id 1 No existe."));

	}
	
	@Test
	void generarCompraExeption4Test() {
		when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		when(formaPagoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(formasPago));
		when(productosRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(producto2));
    	when(invetnatarioRepository.findByTblTiendaVendedorByIdVendedorIdVendedorAndTblProductosByIdProductoIdProducto(Mockito.anyInt(), Mockito.anyInt())).thenReturn(Optional.of(inventario));

		 //Mandamos a probar la exception
    	Response<CompraDTO> response = comprasService.generarCompra(compraDTO);
    	
    	assertTrue(response.isSuccess());
        assertEquals(Constantes.MENSAJE_EXITO_COMPRA, response.getMessage());
        assertNotNull(response.getData());


	}
	
}
