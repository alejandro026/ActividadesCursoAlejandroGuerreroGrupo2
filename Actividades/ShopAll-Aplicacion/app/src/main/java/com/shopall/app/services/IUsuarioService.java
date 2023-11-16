package com.shopall.app.services;

import com.shopall.app.models.dto.UsuarioActualizaDTO;
import com.shopall.app.models.dto.UsuarioDTO;
import com.shopall.app.models.dto.UsuarioRegistroDTO;
import com.shopall.app.models.entity.Response;

public interface IUsuarioService {

    Response<UsuarioDTO> getUsuarios();

    Response<UsuarioRegistroDTO> guardarUsuario(UsuarioRegistroDTO usuarioDTO);

    Response<UsuarioDTO> buscarUsuarioPorId(Integer id);

    Response<UsuarioDTO> actualizarUsuario(UsuarioActualizaDTO usuarioDTO);

    Response<UsuarioDTO> eliminarUsuarioPorId(Integer id);


}
