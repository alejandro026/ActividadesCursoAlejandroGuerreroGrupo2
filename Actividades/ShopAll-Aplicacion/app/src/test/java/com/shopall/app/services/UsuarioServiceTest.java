package com.shopall.app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import com.shopall.app.models.dto.UsuarioActualizaDTO;
import com.shopall.app.models.dto.UsuarioDTO;
import com.shopall.app.models.dto.UsuarioRegistroDTO;
import com.shopall.app.models.entity.Producto;
import com.shopall.app.models.entity.Response;
import com.shopall.app.models.entity.Usuario;
import com.shopall.app.repository.IUsuarioRepository;
import com.shopall.app.utils.Constantes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
	private UsuarioService usuarioService;
	
	@Mock
	private IUsuarioRepository iUsuarioRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	List<Usuario> listUsuarios;
	Usuario usuario;
	UsuarioRegistroDTO usuarioRegistroDTO;
	UsuarioActualizaDTO usuarioActualizaDTO;
	
	@BeforeEach
	public void setUp() {
	   	listUsuarios= new ArrayList<>();
	   	usuario= new Usuario();
		listUsuarios.add(usuario);
		
		usuarioRegistroDTO= new UsuarioRegistroDTO();
		
		usuarioActualizaDTO = new UsuarioActualizaDTO();
		usuarioActualizaDTO.setApMaterno("Prueba");
		usuarioActualizaDTO.setApPaterno("Prueba");
		usuarioActualizaDTO.setCorreo("q@gmail.com");
		usuarioActualizaDTO.setDireccion("Nueva");
		usuarioActualizaDTO.setEstatus(1);
		usuarioActualizaDTO.setIdUsuario(1);
		usuarioActualizaDTO.setNombre("nombre");
		usuarioActualizaDTO.setNoTelefono("44545454");
		usuarioActualizaDTO.setPassword("yruru");
		usuarioActualizaDTO.setTipoUsuario("Nuevo");
		usuarioActualizaDTO.getApMaterno();
		usuarioActualizaDTO.getApPaterno();
		usuarioActualizaDTO.getCorreo();
		usuarioActualizaDTO.getDireccion();
		usuarioActualizaDTO.getEstatus();
		usuarioActualizaDTO.getIdUsuario();
		usuarioActualizaDTO.getNombre();
		usuarioActualizaDTO.getNoTelefono();
		usuarioActualizaDTO.getPassword();
		usuarioActualizaDTO.getTipoUsuario();
	}
	    
	 @Test
	 void getUsuariosTest() {
		 when(iUsuarioRepository.findAll()).thenReturn(listUsuarios);
//		 when(model)
		 Response<UsuarioDTO> responseDto= usuarioService.getUsuarios();
		 assertTrue(responseDto.isSuccess());
	     assertNotNull(responseDto.getList());
	     assertEquals(Constantes.USUARIOS_OBTENIDOS, responseDto.getMessage());

	 }
	 
	 @Test
	 void guardarUsuarioTest() {
		 when(iUsuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
	            Usuario p = invocation.getArgument(0);
	            p.setIdUsuario(1);// Simular ID generado
	            return p;
	        });
		 Response<UsuarioRegistroDTO> response= usuarioService.guardarUsuario(usuarioRegistroDTO);
		 assertTrue(response.isSuccess());
	     assertNotNull(response.getData());
	     assertEquals(Constantes.USUARIO_REGISTRADO, response.getMessage());

	 }
	 
	 @Test
	 void buscarUsuarioPorIdTest() {
		 when(iUsuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		 Response<UsuarioDTO> response= usuarioService.buscarUsuarioPorId(1);
		 assertTrue(response.isSuccess());
	     assertEquals(Constantes.USUARIO_OBTENIDO, response.getMessage());

	 }
	 @Test
	 void actualizarUsuarioTest() {
		 when(iUsuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		 Response<UsuarioDTO> response= usuarioService.actualizarUsuario(usuarioActualizaDTO);
		 assertTrue(response.isSuccess());
		 assertEquals(HttpStatus.OK.value(), response.getCode());
		 assertEquals(Constantes.USUARIO_ACTUALIZADO, response.getMessage());
		 
	 }
	 
	 @Test
	 void eliminarUsuarioPorIdTest() {
		 when(iUsuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		 Response response= usuarioService.eliminarUsuarioPorId(1);
		 assertTrue(response.isSuccess());
		 assertEquals(HttpStatus.OK.value(), response.getCode());
		 assertEquals(Constantes.USUARIO_ELIMINADO, response.getMessage());
		 
	 }
	
}
