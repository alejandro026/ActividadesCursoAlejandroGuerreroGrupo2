package com.shopall.app.services;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shopall.app.exceptions.BusinessException;
import com.shopall.app.models.dto.ProductoAddInventario;
import com.shopall.app.models.dto.ProductoDTO;
import com.shopall.app.models.entity.Categoria;
import com.shopall.app.models.entity.Inventario;
import com.shopall.app.models.entity.Producto;
import com.shopall.app.models.entity.Response;
import com.shopall.app.models.entity.TiendaVendedor;
import com.shopall.app.repository.ICategoriaRepository;
import com.shopall.app.repository.IInvetnatarioRepository;
import com.shopall.app.repository.IProductosRepository;
import com.shopall.app.repository.ITiendaVendedorRepository;
import com.shopall.app.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class ProductosServiceTest {
	
	@InjectMocks
    private ProductosService productoService;
	
	@Mock
    private IProductosRepository productoRepository;

    @Mock
    private ICategoriaRepository categoriaRepository;

    @Mock
    private ITiendaVendedorRepository tiendaVendedorRepository;

    @Mock
    private IInvetnatarioRepository iInvetnatarioRepository;

    
    private ProductoDTO productoDTO;
    private Producto producto;
    private Categoria categoria;
    private TiendaVendedor tiendaVendedor2;
    private ProductoAddInventario productoAddInventario;
    private Inventario inventario;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setNombreCategoria("Electrónica");

        productoDTO = new ProductoDTO();
        productoDTO.setDescripcion("Smartphone de última generación");
        productoDTO.setNombre("Smartphone XYZ");
        productoDTO.setPrecio(999.99);
        productoDTO.setIdCategoria(categoria.getIdCategoria());
        productoDTO.getDescripcion();
        productoDTO.getNombre();
        productoDTO.getPrecio();
        productoDTO.getIdCategoria();

        producto = new Producto();
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCategoria(categoria);
        producto.getDescripcion();
        producto.getNombre();
        producto.getPrecio();
        producto.getCategoria();
        
        tiendaVendedor2= new TiendaVendedor();
        productoAddInventario = new ProductoAddInventario();
        inventario = new Inventario();
    }
    
    @Test
    void guardarProducto() {
    	Optional<Categoria> optional = Optional.of(categoria);
    	Optional<TiendaVendedor> optionalTienda= Optional.of(tiendaVendedor2);
    	when(categoriaRepository.findById(Mockito.any())).thenReturn(optional);
    	when(tiendaVendedorRepository.findById(Mockito.any())).thenReturn(optionalTienda);
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> {
            Producto p = invocation.getArgument(0);
            p.setIdProducto(1); // Simular ID generado
            return p;
        });
    	Response<ProductoDTO> response = productoService.guardarProducto(productoDTO);

    	 // Verificar los resultados
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(Constantes.MENSAJE_REGISTRO_PRODUCTO + "1", response.getMessage());

        // Verificar la interacción con los mocks
        verify(categoriaRepository).findById(any(Integer.class));
        verify(productoRepository).save(any(Producto.class));
    }
    
    @Test
    void guardarProductoExeption1Test() {
        //Mandamos a probar la exception
    	BusinessException thrown = assertThrows(
    			BusinessException.class,
                () -> productoService.guardarProducto(productoDTO),
                Constantes.PRODUCTO_NO_EXISTE
        );
    	
    	assertTrue(thrown.getMensaje().contains("No existe la categoria"));
    	
    }
    
    @Test
    void guardarProductoExeption2Test() {
    	
    	Optional<Categoria> optional = Optional.of(categoria);
    	when(categoriaRepository.findById(Mockito.any())).thenReturn(optional);

    	
        //Mandamos a probar la exception
    	BusinessException thrown = assertThrows(
    			BusinessException.class,
                () -> productoService.guardarProducto(productoDTO),
                Constantes.VENDEDOR_NO_EXISTENTE
        );
//        assertThrows(BusinessException.class, () -> comprasService.generarCompra(compraDto));

    	assertTrue(thrown.getMensaje().contains("No existe el vendedor"));
    }
    
    @Test
    void actualizaStockAlInventarioTest() {
    	Optional<Inventario> optionalInventario= Optional.of(inventario);
    	doReturn(optionalInventario).when(iInvetnatarioRepository).findByTblTiendaVendedorByIdVendedorIdVendedorAndTblProductosByIdProductoIdProducto(null, null);

    	Response<ProductoAddInventario> response = productoService.actualizaStockAlInventario(productoAddInventario);
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(Constantes.STOCK_ACTUALIZADO, response.getMessage());

    }

    @Test
    void actualizaStockAlInventarioElseTest() {
    	Optional<Inventario> optionalInventario= Optional.empty();
    	doReturn(optionalInventario).when(iInvetnatarioRepository).findByTblTiendaVendedorByIdVendedorIdVendedorAndTblProductosByIdProductoIdProducto(null, null);
    	when(tiendaVendedorRepository.findById(Mockito.any())).thenReturn(Optional.of(tiendaVendedor2));
    	when(productoRepository.findById(Mockito.any())).thenReturn(Optional.of(producto));
    	Response<ProductoAddInventario> response = productoService.actualizaStockAlInventario(productoAddInventario);
    
    	assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(Constantes.STOCK_ACTUALIZADO, response.getMessage());
}
    
    @Test
    void actualizaStockAlInventarioExeptionTest() {
    	Optional<Inventario> optionalInventario= Optional.empty();
    	doReturn(optionalInventario).when(iInvetnatarioRepository).findByTblTiendaVendedorByIdVendedorIdVendedorAndTblProductosByIdProductoIdProducto(null, null);
    	when(tiendaVendedorRepository.findById(Mockito.any())).thenReturn(Optional.of(tiendaVendedor2));
    	
        //Mandamos a probar la exception
    	BusinessException thrown = assertThrows(
    			BusinessException.class,
                () -> productoService.actualizaStockAlInventario(productoAddInventario),
                Constantes.PRODUCTO_NO_EXISTE
        );
    	assertTrue(thrown.getMensaje().contains("No existe el producto en la BD"));

//    	Response<ProductoAddInventario> response = productoService.actualizaStockAlInventario(productoAddInventario);
    }

}
