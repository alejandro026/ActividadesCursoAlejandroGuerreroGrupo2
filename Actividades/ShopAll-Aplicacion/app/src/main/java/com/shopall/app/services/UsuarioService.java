package com.shopall.app.services;

import com.shopall.app.exceptions.BusinessException;
import com.shopall.app.models.dto.UsuarioDTO;
import com.shopall.app.models.dto.UsuarioRegistroDTO;
import com.shopall.app.models.entity.Response;
import com.shopall.app.models.entity.Usuario;
import com.shopall.app.repository.IUsuarioRepository;
import com.shopall.app.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Response<UsuarioDTO> getUsuarios() {
        List<UsuarioDTO> listaUsuariosDto= new ArrayList<>();
        Response<UsuarioDTO> response=  new Response<>();
        List<Usuario> listaUsuarios= usuarioRepository.findAll();

        for(Usuario user: listaUsuarios){
            //Agregando usuarios a la lista
            UsuarioDTO usuarioDto = usuarioToUsuarioDTO(user);
            listaUsuariosDto.add(usuarioDto);
        }
        response.setList(listaUsuariosDto);
        response.setCode(HttpStatus.OK.value());
        response.setSuccess(true);
        response.setMessage(Constantes.USUARIOS_OBTENIDOS);
        return response;
    }

    @Override
    public Response<UsuarioRegistroDTO> guardarUsuario(UsuarioRegistroDTO usuarioDTO) {
        Response<UsuarioRegistroDTO> response= new Response<>();
        try{
            Usuario usuario= new Usuario();
            usuario.setDireccion(usuarioDTO.getDireccion());
            usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
            usuario.setCorreo(usuarioDTO.getCorreo());
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setNoTelefono(usuarioDTO.getNoTelefono());
            usuario.setApMaterno(usuarioDTO.getApMaterno());
            usuario.setApPaterno(usuarioDTO.getApPaterno());
            usuario.setEstatus(usuarioDTO.getEstatus());
            usuario.setPassword(usuarioDTO.getPassword());
            //Guardando en la base de datos
            usuario= usuarioRepository.save(usuario);

            usuarioDTO.setIdUsuario(usuario.getIdUsuario());

            response.setMessage(Constantes.USUARIO_REGISTRADO);
            response.setData(usuarioDTO);
            response.setCode(HttpStatus.OK.value());
            response.setSuccess(true);
        }catch (RuntimeException ex){
            //Manejando la exepcion de duplicados
            String errorMessage = ex.getMessage();
            if (errorMessage.contains(Constantes.CONSTRAINT_EMAIL)) {
                throw new BusinessException(HttpStatus.CONFLICT, Constantes.CORREO_DUPLICADO);
            } else if (errorMessage.contains(Constantes.CONSTRAINT_TELEFONO)) {
                throw new BusinessException(HttpStatus.CONFLICT, Constantes.TELEFONO_DUPLICADO);
            }else{
                throw new BusinessException(HttpStatus.NOT_FOUND, Constantes.ERROR_GENERICO);
            }
        }
        return response;
    }

    @Override
    public Response<UsuarioDTO> buscarUsuarioPorId(Integer id) {
        Response<UsuarioDTO> response= new Response<>();

        Optional<Usuario> usuarioBuscado= usuarioRepository.findById(id);
        if(usuarioBuscado.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND, Constantes.USUARIO_NO_EXISTENTE);
        }
        UsuarioDTO usuarioDTO = usuarioToUsuarioDTO(usuarioBuscado.get());
        response.setSuccess(true);
        response.setMessage(Constantes.USUARIO_OBTENIDO);
        response.setData(usuarioDTO);
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @Override
    public Response<UsuarioRegistroDTO> actualizarUsuario(UsuarioRegistroDTO usuarioDTO) {
        return null;
    }

    @Override
    public void eliminarUsuarioPorId(Integer id) {

    }

    //Metodo para convertir usuario a usuarioDTO
    private UsuarioDTO usuarioToUsuarioDTO(Usuario usuario){
        UsuarioDTO usuarioDto = new UsuarioDTO();

        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setApPaterno(usuario.getApPaterno());
        usuarioDto.setApMaterno(usuario.getApMaterno());
        usuarioDto.setCorreo(usuario.getCorreo());
        usuarioDto.setDireccion(usuario.getDireccion());
        usuarioDto.setNoTelefono(usuario.getNoTelefono());

        return usuarioDto;
    }

}
