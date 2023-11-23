//package com.shopall.app.services;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.shopall.app.models.dto.UsuarioDTO;
//import com.shopall.app.models.entity.Response;
//import com.shopall.app.repository.IUsuarioRepository;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//
//@ExtendWith(MockitoExtension.class)
//public class UsuarioServiceTest {
//
//    @InjectMocks
//	private UsuarioService usuarioService;
//	
//	@Mock
//	private IUsuarioRepository iUsuarioRepository;
//	
//	Response<UsuarioDTO> response;
//	
//	@BeforeEach
//	public void setUp() {
//	   	response = new Response<>();
//	}
//	    
//	 @Test
//	 void getUsuariosTest() {
//		 Response<UsuarioDTO> responseDto= usuarioService.getUsuarios();
//		 assertNotNull(responseDto);
////	   	taskList.add(new Task());
////	   	when(iTaskRepository.findAll()).thenReturn(taskList);
////	   	Response<Task> response= taskService.findAllTask();
////	   	assertNotNull(response);
//	 }
//	
//}
