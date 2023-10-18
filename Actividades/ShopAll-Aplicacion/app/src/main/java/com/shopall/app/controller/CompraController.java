/**
 * 
 */
package com.shopall.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopall.app.models.dto.CompraDTO;
import com.shopall.app.models.entity.Response;
import com.shopall.app.services.IComprasService;

import jakarta.validation.Valid;

/**
 * @author Alejandro Guerrero
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/compras")
@Validated
public class CompraController {
	
	@Autowired
	private IComprasService comprasService;
	
    @PostMapping(path = "/guardar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<CompraDTO>> guardar(@Valid @RequestBody CompraDTO compraDTO){

        Response<CompraDTO> response = comprasService.generarCompra(compraDTO);

        // Retorna una respuesta con el producto y el estado HTTP OK (200)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
