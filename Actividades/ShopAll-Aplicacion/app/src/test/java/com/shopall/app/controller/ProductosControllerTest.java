package com.shopall.app.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.shopall.app.models.dto.ProductoAddInventario;
import com.shopall.app.models.dto.ProductoDTO;
import com.shopall.app.models.entity.Response;
import com.shopall.app.services.IProductosService;

@ExtendWith(MockitoExtension.class)
public class ProductosControllerTest {
	
	@InjectMocks
    private ProductosController controller;
    
    @Mock
    private IProductosService iProductosService;
    
    Response<ProductoDTO> response;
    ProductoDTO productoDTO;
    Response<ProductoAddInventario> response2;
    ProductoAddInventario productoAddInventario;

    @BeforeEach
    public void setUp() {
    	response= new Response<>();
    	productoDTO = new ProductoDTO();
    	response2= new Response<>();
    	productoAddInventario= new ProductoAddInventario();
    }
    
    @Test
    void guardarProductoTest() {
    	when(iProductosService.guardarProducto(Mockito.any())).thenReturn(response);
    	ResponseEntity<Response<ProductoDTO>> response= controller.guardarProducto(productoDTO);
        assertNotNull(response);
    }
    
    @Test
    void actualizarStockTest() {
    	when(iProductosService.actualizaStockAlInventario(Mockito.any())).thenReturn(response2);
    	ResponseEntity<Response<ProductoAddInventario>> response= controller.actualizarStock(productoAddInventario);
        assertNotNull(response);
    }
}
