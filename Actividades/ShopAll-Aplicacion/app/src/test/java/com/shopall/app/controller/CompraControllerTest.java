package com.shopall.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.shopall.app.models.dto.CompraDTO;
import com.shopall.app.models.entity.Response;
import com.shopall.app.services.IComprasService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CompraControllerTest {
	
    @InjectMocks
    private CompraController compraController;
    
    @Mock
    private IComprasService iComprasService;
    
    Response<CompraDTO> response;
    CompraDTO compraDTO;

    @BeforeEach
    public void setUp() {
    	response= new Response<>();
    	compraDTO = new CompraDTO();
    }
    
    @Test
    void guardarTest() {
    	when(iComprasService.generarCompra(Mockito.any())).thenReturn(response);
    	ResponseEntity<Response<CompraDTO>> response= compraController.guardar(compraDTO);
        assertNotNull(response);
    }

}
