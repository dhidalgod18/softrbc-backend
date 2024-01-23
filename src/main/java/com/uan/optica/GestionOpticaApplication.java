package com.uan.optica;

import com.uan.optica.entities.Usuario;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GestionOpticaApplication implements CommandLineRunner {
	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(GestionOpticaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario nuevo = new Usuario();
		nuevo.setNombre("Daniela");
		nuevo.setApellido("Hidalgo");
		nuevo.setCorreo("dhidalgo01@uan.edu.co");
		nuevo.setDireccion("cra20");
		nuevo.setTelefono(3103241221l);
		nuevo.setContraseña("dani");
		nuevo.setRol("Paciente");

		usuarioService.crearPersona(nuevo);

		List<Usuario> usuarios = usuarioService.obtenerTodas();

		usuarios.forEach(u->System.out.println("Nombre de la persona: "+ u.getNombre()));

	}
}
