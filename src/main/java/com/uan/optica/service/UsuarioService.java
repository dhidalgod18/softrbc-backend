package com.uan.optica.service;

import com.uan.optica.entities.Usuario;
import com.uan.optica.entities.UsuarioOptometraDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UsuarioService {


    boolean crearPersona(Usuario usuario);
    List<UsuarioOptometraDTO> obtenerUsuariosOptometraDTO();
    boolean modificarDatosOptometra(int idUsuario, String nuevadireccion, String nuevocorreo, Long nuevotelefono);

    boolean cambiarEstadoUsuario(int idUsuario);
    Usuario obtenerUsuarioPorCorreo(String correo);
    boolean actualizarContrasena(int idUsuario, String nuevaContraseña);

    Usuario obtenerUsuarioCedula(Long cedula);
    Usuario obtener(int id);

    int obtenerAdmin();









}
