package com.shopall.app.controller;

import com.shopall.app.models.dto.ProductoAddInventario;
import com.shopall.app.models.dto.ProductoDTO;
import com.shopall.app.models.entity.Response;
import com.shopall.app.services.IProductosService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/productos")
@Validated
public class ProductosController {

    @Autowired
    private IProductosService productosService;

    /**
     * Maneja la solicitud para guardar un nuevo producto.
     *
     * @return ResponseEntity el usuario guardado.
     */
    @PostMapping(path = "/guardarProducto", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<ProductoDTO>> guardarProducto(@Valid @RequestBody ProductoDTO productoDTO){

        Response<ProductoDTO> response = productosService.guardarProducto(productoDTO);

        // Retorna una respuesta con el producto y el estado HTTP OK (200)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Maneja la solicitud para guardar un nuevo producto.
     *
     * @return ResponseEntity el usuario guardado.
     */
    @PutMapping(path = "/actualizarStock", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<ProductoAddInventario>> actualizarStock(@Valid @RequestBody ProductoAddInventario productoDTO){

        Response<ProductoAddInventario> response = productosService.actualizaStockAlInventario(productoDTO);

        // Retorna una respuesta con el producto y el estado HTTP OK (200)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
