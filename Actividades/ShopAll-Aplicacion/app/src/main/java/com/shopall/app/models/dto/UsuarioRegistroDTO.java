package com.shopall.app.models.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class UsuarioRegistroDTO {
    private int idUsuario;

    @NotBlank(message = "Debe ingresar una nombre valido")
    private String nombre;

    @NotBlank(message = "Debe ingresar una apellido materno valido")
    private String apMaterno;

    @NotBlank(message = "Debe ingresar una apellido paterno valido")
    private String apPaterno;

    @Email(message = "Debe ingresar un correo valido")
    @NotBlank(message = "Debe ingresar un correo")
    private String correo;

    private String direccion;

    @NotBlank(message = "Debe ingresar un numero de telefono valido")
    private String noTelefono;

    @NotBlank(message = "Debe un tipo de usuario valido")
    private String tipoUsuario;

    @NotBlank(message = "Debe ingresar una contraseña valida")
    private String password;

    @NotNull(message = "Debe ingresar un estatus")
    @Positive(message = "Debe ingresar una estatus valida")
    @Min(0)
    @Max(1)
    private Integer estatus;


}
