package com.uan.optica.controller;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Usuario;
import com.uan.optica.service.EnvioCorreoService;
import com.uan.optica.service.OptometraService;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.uan.optica.filtros.PasswordAleatorio.generarPassword;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OptometraService optometraService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EnvioCorreoService envioCorreoService;


/**
    @GetMapping("/listaOptometras")
    public List<Usuario> listaOptometras() {
        List<Usuario> optometras = usuarioService.obtenerUsuariosOptometra();
        for (Usuario optometra : optometras) {
            System.out.println("Nombre: " + optometra.getNombre() + ", Correo: " + optometra.getCorreo() + ", Rol: " + optometra.getRol());
        }
        return optometras;
    }*/

    @GetMapping("/listaOptometras")
    public List<Usuario> listaOptometras() {
        return usuarioService.obtenerUsuariosOptometra();
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> editarDatosOptometra(@PathVariable("id") int id, @RequestBody Map<String, Object> requestBody) {
        String nuevadireccion = (String) requestBody.get("nuevadireccion");
        String nuevocorreo = (String) requestBody.get("nuevocorreo");
        String nuevotelefono = (String) requestBody.get("nuevotelefono");

        if (nuevadireccion == null || nuevocorreo == null || nuevotelefono == null) {
            return ResponseEntity.badRequest().body("Falta información requerida");
        }

        boolean resultado = usuarioService.modificarDatosOptometra(id, nuevadireccion, nuevocorreo, nuevotelefono);

        if (resultado) {
            return ResponseEntity.ok("Datos del optometra actualizados exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar los datos del optometra");
        }
    }
    @PostMapping("/nueva")
    public ResponseEntity<?> guardarNuevaUsuario(@RequestBody Map<String, Object> requestBody) {
        try {

            // Extraer los datos del usuario del cuerpo de la solicitud
            Map<String, Object> usuarioMap = (Map<String, Object>) requestBody.get("usuario");
            System.out.println("Usuario: " + usuarioMap);
            // Crear un objeto Usuario a partir de los datos recibidos
            Usuario usuario = new Usuario();
            usuario.setNombre((String) usuarioMap.get("nombre"));
            usuario.setApellido((String) usuarioMap.get("apellido"));
            usuario.setCorreo((String) usuarioMap.get("correo"));
            usuario.setDireccion((String) usuarioMap.get("direccion"));
            usuario.setTelefono(Long.parseLong((String) usuarioMap.get("telefono")));
            String pass = generarPassword();
            System.out.println(pass + "password que genero");
            String respass = passwordEncoder.encode(pass);
            System.out.println(respass + "password encriptada");
            usuario.setPassword(respass);
            usuario.setCedula(Long.parseLong((String) usuarioMap.get("cedula")));
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
            optometra.setNumerotarjeta(numeroTarjeta);

            optometra.setIdusuario(usuario.getIdusuario()); // Asignar el ID del usuario

            optometra.setActivo(true);
            optometraService.crearOptometra(optometra);
            int idUsuarioAsignado = optometra.getIdusuario();
            System.out.println(idUsuarioAsignado+"id tarjeta");



            envioCorreoService.enviarCorreo(usuario.getCorreo(), "Registro exitoso", usuario.getCorreo(), pass);

            return ResponseEntity.ok().build(); // Registro exitoso


        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar usuario: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PutMapping("/cambiarEstado/{id}")
    public ResponseEntity<?> editarEstadoOptometra(@PathVariable("id") int id) {
        boolean resultado = usuarioService.cambiarEstadoUsuario(id);

        if (resultado) {
            return ResponseEntity.ok("El estado del optometra ha sido cambiado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo cambiar el estado del optometra");
        }
    }



}

