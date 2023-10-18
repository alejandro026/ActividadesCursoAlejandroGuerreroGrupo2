package com.shopall.app.services;

import com.shopall.app.exceptions.BusinessException;
import com.shopall.app.models.dto.UsuarioActualizaDTO;
import com.shopall.app.models.dto.UsuarioDTO;
import com.shopall.app.models.dto.UsuarioRegistroDTO;
import com.shopall.app.models.entity.Response;
import com.shopall.app.models.entity.Usuario;
import com.shopall.app.repository.IUsuarioRepository;
import com.shopall.app.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response<UsuarioDTO> getUsuarios() {
        List<UsuarioDTO> listaUsuariosDto= new ArrayList<>();
        Response<UsuarioDTO> response=  new Response<>();
        List<Usuario> listaUsuarios= usuarioRepository.findAll();

        for(Usuario user: listaUsuarios){
            //Agregando usuarios a la lista
            UsuarioDTO usuarioDto = convertToDto(user);
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
        UsuarioDTO usuarioDTO = convertToDto(usuarioBuscado.get());
        response.setSuccess(true);
        response.setMessage(Constantes.USUARIO_OBTENIDO);
        response.setData(usuarioDTO);
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @Override
    @Transient
    public Response<UsuarioDTO> actualizarUsuario(UsuarioActualizaDTO usuarioDTO) {
        Response<UsuarioDTO> response= new Response<>();
        Optional<Usuario> usuarioOptional = Optional.empty();
        Usuario usuario=null;
        UsuarioDTO usuarioRegresaDTO = new UsuarioDTO();
        try{
            usuarioOptional= usuarioRepository.findById(usuarioDTO.getIdUsuario());
        }catch (DataAccessException ex){
            log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
                    ex.getStackTrace());
        }
        //Validando de que el usuario exista.
        if(usuarioOptional.isEmpty()){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "El  usuario con Id " + usuarioDTO.getIdUsuario() + " no existe");
        }

        usuario= usuarioOptional.get();

        if(validaCadena(usuarioDTO.getTipoUsuario())){
            usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        }

        if(usuarioDTO.getEstatus()!=null){
            usuario.setEstatus(usuarioDTO.getEstatus());
        }

        if(validaCadena(usuarioDTO.getCorreo())){
            usuario.setCorreo(usuarioDTO.getCorreo());
        }

        if(validaCadena(usuarioDTO.getDireccion())){
            usuario.setDireccion(usuarioDTO.getDireccion());
        }

        if(validaCadena(usuarioDTO.getPassword())){
            usuario.setPassword(usuarioDTO.getPassword());
        }

        if(validaCadena(usuarioDTO.getNombre())){
            usuario.setNombre(usuarioDTO.getNombre());
        }

        if(validaCadena(usuarioDTO.getApMaterno())){
            usuario.setApMaterno(usuarioDTO.getApMaterno());
        }

        if(validaCadena(usuarioDTO.getApPaterno())){
            usuario.setApPaterno(usuarioDTO.getApPaterno());
        }

        if(validaCadena(usuarioDTO.getApMaterno())){
            usuario.setApMaterno(usuarioDTO.getApMaterno());
        }

        if(validaCadena(usuarioDTO.getNoTelefono())){
            usuario.setNoTelefono(usuarioDTO.getNoTelefono());
        }
        usuarioRegresaDTO= convertToDto(usuario);

        usuarioRepository.save(usuario);

        response.setMessage(Constantes.USUARIO_ACTUALIZADO);
        response.setData(usuarioRegresaDTO);
        response.setCode(HttpStatus.OK.value());
        response.setSuccess(true);

        return response;
    }

    @Override
    public Response eliminarUsuarioPorId(Integer id) {
        Response response= new Response();
        Optional<Usuario> usuario = Optional.empty();
        try{
            usuario= usuarioRepository.findById(id);
        }catch (DataAccessException ex){
            log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
                    ex.getStackTrace());
        }
        //Validando de que el usuario exista.
        if(usuario.isEmpty()){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "El  usuario con Id " + id + " no existe");
        }

        //Eliminado el usuario
        try {
            usuarioRepository.delete(usuario.get());
        } catch (DataAccessException ex) {
            log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
                    ex.getStackTrace());
        }

        response.setCode(HttpStatus.OK.value());
        response.setSuccess(true);
        response.setMessage(Constantes.USUARIO_ELIMINADO);

        return response;

    }

    private boolean validaCadena(String cadena){
        if(cadena!=null ){
            return !"".equals(cadena);
        }else {
            return false;
        }
    }

    //Método para convertir usuario a usuarioDTO
    private UsuarioDTO convertToDto(Usuario pE) {
        return modelMapper.map(pE, UsuarioDTO.class);
    }

}
