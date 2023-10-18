package com.shopall.app.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class UsuarioActualizaDTO {

    @NotNull(message = "Debe ingresar un id de usuario")
    @Positive(message = "Debe ingresar id valido")
    private int idUsuario;
    private String nombre;
    private String apMaterno;
    private String apPaterno;
    private String correo;
    private String direccion;
    private String noTelefono;
    private String tipoUsuario;
    private String password;
    private Integer estatus;

}
