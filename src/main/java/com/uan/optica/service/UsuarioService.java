package com.uan.optica.service;

import com.uan.optica.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UsuarioService {


    boolean crearPersona(Usuario usuario);
    List<Usuario> obtenerTodas();






}
