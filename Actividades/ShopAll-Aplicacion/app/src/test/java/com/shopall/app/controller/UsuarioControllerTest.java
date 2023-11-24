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

import com.shopall.app.models.dto.UsuarioActualizaDTO;
import com.shopall.app.models.dto.UsuarioDTO;
import com.shopall.app.models.dto.UsuarioRegistroDTO;
import com.shopall.app.models.entity.Response;
import com.shopall.app.services.IUsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

	@InjectMocks
	private UsuarioController controller;
	
	@Mock
	private IUsuarioService iUsuarioService;
	
	Response<UsuarioDTO> response;
	Response<UsuarioRegistroDTO> response2;
	UsuarioRegistroDTO usuarioRegistroDTO;
	UsuarioActualizaDTO usuarioActualizaDTO;
	
    @BeforeEach
    public void setUp() {
    	response= new Response<>();
    	usuarioRegistroDTO = new UsuarioRegistroDTO();
    	usuarioActualizaDTO = new UsuarioActualizaDTO();
    	response2 = new Response<>();
    }
    
    @Test
    void buscarPorIdTest() {
    	when(iUsuarioService.buscarUsuarioPorId(Mockito.anyInt())).thenReturn(response);
    	ResponseEntity<Response<UsuarioDTO>> response= controller.buscarPorId(1);
        assertNotNull(response);
    }
    
    @Test
    void eliminarPorIdTest() {
    	when(iUsuarioService.eliminarUsuarioPorId(Mockito.anyInt())).thenReturn(response);
    	ResponseEntity<Response> response= controller.eliminarPorId(1);
        assertNotNull(response);
    }
    
    @Test
    void actualizaUsuarioTest() {
    	when(iUsuarioService.actualizarUsuario(Mockito.any())).thenReturn(response);
    	ResponseEntity<Response<UsuarioDTO>> response= controller.actualizaUsuario(usuarioActualizaDTO);
        assertNotNull(response);
    }
    
    @Test
    void findAllUsersTest() {
    	when(iUsuarioService.getUsuarios()).thenReturn(response);
    	ResponseEntity<Response<UsuarioDTO>> response= controller.findAllUsers();
        assertNotNull(response);
    }
    
    @Test
    void guardarUsuarioTest() {
    	when(iUsuarioService.guardarUsuario(Mockito.any())).thenReturn(response2);
    	ResponseEntity<Response<UsuarioRegistroDTO>> response= controller.guardarUsuario(usuarioRegistroDTO);
        assertNotNull(response);
    }
    
}
