package com.uan.optica.controller;

import com.uan.optica.entities.Usuario;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/nueva")
    public void guardarNuevaUsuario(@RequestBody Usuario usuario) {
        usuario.setRol("optometra");
        System.out.println("USUARIOOOOO " + usuario.toString());
        usuarioService.crearPersona(usuario);
    }
}
