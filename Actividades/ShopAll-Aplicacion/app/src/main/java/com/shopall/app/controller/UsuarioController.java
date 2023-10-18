package com.shopall.app.controller;

import com.shopall.app.models.dto.UsuarioDTO;
import com.shopall.app.models.dto.UsuarioRegistroDTO;
import com.shopall.app.models.entity.Response;
import com.shopall.app.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    /**
     * Maneja la solicitud para obtener todos los componentes existentes.
     *
     * @return ResponseEntity con una lista de usuarios en el cuerpo de la respuesta.
     */
    @GetMapping(path = "/getUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UsuarioDTO>> findAllPost(){
        Response<UsuarioDTO> response = usuarioService.getUsuarios();

        // Retorna una respuesta con la lista de usuarios y el estado HTTP OK (200)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Maneja la solicitud para guardar un nuevo usuario.
     *
     * @return ResponseEntity el usuario guardado.
     */
    @PostMapping(path = "/guardarUsuario", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UsuarioRegistroDTO>> guardarUsuario(@RequestBody UsuarioRegistroDTO usuarioDto){
        Response<UsuarioRegistroDTO> response = usuarioService.guardarUsuario(usuarioDto);

        // Retorna una respuesta con el usuario y el estado HTTP OK (200)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Maneja la solicitud para obtener todos los componentes existentes.
     *
     * @return ResponseEntity con una lista de usuarios en el cuerpo de la respuesta.
     */
    @GetMapping(path = "/buscarPorId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UsuarioDTO>> buscarPorId(@PathVariable Integer id){
        Response<UsuarioDTO> response = usuarioService.buscarUsuarioPorId(id);

        // Retorna una respuesta con la lista de usuarios y el estado HTTP OK (200)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
