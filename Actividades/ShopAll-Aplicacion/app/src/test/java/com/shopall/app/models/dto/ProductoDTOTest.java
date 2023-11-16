package com.shopall.app.models.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;;

public class ProductoDTOTest {


    private ProductoDTO productoDTO;

    @BeforeEach
    public void setUp() {
        productoDTO = new ProductoDTO();
    }

    @Test
    public void testGetSetId() {
        assertNull(productoDTO.getIdProducto());
        productoDTO.setIdProducto(1);
        assertEquals(1, productoDTO.getIdProducto());
    }

    @Test
    public void testGetSetNombre() {
        assertNull(productoDTO.getNombre());
        productoDTO.setNombre("Producto 1");
        assertEquals("Producto 1", productoDTO.getNombre());
    }

    @Test
    public void testGetSetDescripcion() {
        assertNull(productoDTO.getDescripcion());
        productoDTO.setDescripcion("Descripción del producto");
        assertEquals("Descripción del producto", productoDTO.getDescripcion());
    }

    @Test
    public void testGetSetPrecioUnitario() {
//        assertNull(productoDTO.getPrecio());
        productoDTO.setPrecio(10.0);
        assertEquals(10.0, productoDTO.getPrecio(), 0.001);
    }

    @Test
    public void testGetSetIdCategoria() {
//        assertEquals(0, productoDTO.getIdCategoria());
        productoDTO.setIdCategoria(1);
        assertEquals(1, productoDTO.getIdCategoria());
    }


}
