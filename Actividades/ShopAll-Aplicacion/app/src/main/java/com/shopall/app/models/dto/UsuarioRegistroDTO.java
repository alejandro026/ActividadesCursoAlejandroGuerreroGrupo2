package com.shopall.app.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRegistroDTO {
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
