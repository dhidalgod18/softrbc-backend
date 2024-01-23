package com.uan.optica.controller;

import com.uan.optica.entities.Usuario;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/usuarios")
@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/nueva")
    public String mostrarFormularioDeRegistroPacientes(Model model){
        model.addAttribute("persona",new Usuario());
        model.addAttribute("accion","/usuarios/nueva");
        return "registro";

    }

    @PostMapping("/nueva")
    public  String guardarNuevaPersona(@ModelAttribute Usuario usuario){
        usuarioService.crearPersona(usuario);
        return "redirect:/usuarios";

    }
}
