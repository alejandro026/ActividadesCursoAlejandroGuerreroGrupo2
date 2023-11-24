package com.shopall.app.models.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class UsuarioDTOTest {

	private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp(){
        usuarioDTO= new UsuarioDTO();
    }


    @Test
    private void testSetId(){
//    	assertNull(usuarioDTO.getIdUsuario());
        usuarioDTO.setIdUsuario(1);
        assertEquals("1", usuarioDTO.getIdUsuario());
    }
    
    @Test
    private void testSetNombre(){
    	assertNull(usuarioDTO.getNombre());
        usuarioDTO.setNombre("Usuario 1");
        assertEquals("Usuario 1", usuarioDTO.getNombre());
    }

    
    @Test
    private void testSetApMaterno(){
    	assertNull(usuarioDTO.getApMaterno());
        usuarioDTO.setApMaterno("Apellido 1");
        assertEquals("Apellido 1", usuarioDTO.getApMaterno());
    }

    
    @Test
    private void testSetApPaterno(){
    	assertNull(usuarioDTO.getApPaterno());
        usuarioDTO.setApPaterno("Apellido 2");
        assertEquals("Apellido 2", usuarioDTO.getApPaterno());
    }

    
    @Test
    private void testSetCorreo(){
    	assertNull(usuarioDTO.getCorreo());
        usuarioDTO.setCorreo("prueba@gmail.com");
        assertEquals("prueba@gmail.com", usuarioDTO.getCorreo());
    }

    
    @Test
    private void testSetDireccion(){
    	assertNull(usuarioDTO.getDireccion());
        usuarioDTO.setDireccion("Calle juarez");
        assertEquals("Calle juarez", usuarioDTO.getDireccion());
    }

    
    @Test
    private void testSetNoTelefono(){
    	assertNull(usuarioDTO.getNoTelefono());
        usuarioDTO.setNoTelefono("1234567890");
        assertEquals("1234567890", usuarioDTO.getNoTelefono());
    }


}
