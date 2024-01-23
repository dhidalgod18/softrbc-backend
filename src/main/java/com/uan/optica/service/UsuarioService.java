package com.uan.optica.service;

import com.uan.optica.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    List<Usuario> obtenerTodas();

    Usuario obtenerPersonaPorID(int id);

    Usuario crearPersona(Usuario usuario);

    Usuario validarInicioSesion(String correo, String contraseña);


}
