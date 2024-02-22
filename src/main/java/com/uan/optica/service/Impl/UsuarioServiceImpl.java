package com.uan.optica.service.Impl;

import com.uan.optica.entities.Usuario;
import com.uan.optica.repository.UsuarioRepository;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean crearPersona(Usuario usuario) {
        try {
            String passwordBc = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(passwordBc);
            usuarioRepository.save(usuario);
             return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Usuario> obtenerTodas() {
        return usuarioRepository.findAll();
    }



}
