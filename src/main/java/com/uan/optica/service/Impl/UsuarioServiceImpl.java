package com.uan.optica.service.Impl;

import com.uan.optica.entities.Usuario;
import com.uan.optica.repository.UsuarioRepository;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> obtenerTodas() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPersonaPorID(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario crearPersona(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario validarInicioSesion(String correo, String contraseña) {
       return usuarioRepository.findByCorreoAndContraseña(correo,contraseña);

    }
}
