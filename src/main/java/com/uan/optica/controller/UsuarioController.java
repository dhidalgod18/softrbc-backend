package com.uan.optica.controller;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Usuario;
import com.uan.optica.service.OptometraService;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OptometraService optometraService;

    @GetMapping
    @ResponseBody
    public List<Usuario> listarPersonas() {
        return usuarioService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public List<Usuario> listarPersonas2() {
        return usuarioService.obtenerTodas();
    }


    @PostMapping("/nueva")
    public ResponseEntity<?> guardarNuevaUsuario(@RequestBody Map<String, Object> requestBody) {
        try {
            // Extraer los datos del usuario del cuerpo de la solicitud
            Map<String, Object> usuarioMap = (Map<String, Object>) requestBody.get("usuario");
            // Crear un objeto Usuario a partir de los datos recibidos
            Usuario usuario = new Usuario();
            usuario.setNombre((String) usuarioMap.get("nombre"));
            usuario.setApellido((String) usuarioMap.get("apellido"));
            usuario.setCorreo((String) usuarioMap.get("correo"));
            usuario.setDireccion((String) usuarioMap.get("direccion"));
            usuario.setTelefono(((Long) usuarioMap.get("telefono")).intValue()); // Convertir a int
            usuario.setPassword((String) usuarioMap.get("password"));
            usuario.setCedula((String) usuarioMap.get("cedula"));
            usuario.setRol("ROLE_OPTOMETRA");

            // Extraer el objeto optometra del cuerpo de la solicitud
            Map<String, Object> optometraMap = (Map<String, Object>) requestBody.get("optometra");
            String numeroTarjeta = (String) optometraMap.get("numeroTarjeta");
            System.out.println(numeroTarjeta+"numero tarjeta");
            // Validar los campos requeridos para el optometra
            if (numeroTarjeta == null) {
                return ResponseEntity.badRequest().body("El número de tarjeta es requerido para el optometra");
            }

            // Crear el usuario
            usuarioService.crearPersona(usuario);

            // Crear el optometra
            Optometra optometra = new Optometra();
            optometra.setNumeroTarjeta(numeroTarjeta);

            optometra.setIdUsuario(usuario.getIdusuario()); // Asignar el ID del usuario

            optometra.setActivo(true);
            optometraService.crearOptometra(optometra);
            int idUsuarioAsignado = optometra.getIdUsuario();
            System.out.println(idUsuarioAsignado+"id tarjeta");



            return ResponseEntity.ok().build(); // Registro exitoso
        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar usuario: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


}
