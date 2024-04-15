package com.uan.optica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uan.optica.entities.Auditoria;
import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Usuario;
import com.uan.optica.entities.UsuarioOptometraDTO;
import com.uan.optica.service.AuditoriaServices;
import com.uan.optica.service.EnvioCorreoService;
import com.uan.optica.service.OptometraService;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.uan.optica.filtros.CodigoRecuperacion.generarCodigoRecuperacion;
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
    @Autowired
    AuditoriaServices auditoriaServices;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String fecha1 = dateFormat.format(new Date());

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
    public ResponseEntity<List<UsuarioOptometraDTO>> obtenerUsuariosOptometra() {
        List<UsuarioOptometraDTO> usuariosOptometraDTO = usuarioService.obtenerUsuariosOptometraDTO()  ;
        return ResponseEntity.ok(usuariosOptometraDTO);
    }
/**
    @GetMapping("/listaOptometras")
    public List<Usuario> listaOptometras() {
        return usuarioService.obtenerUsuariosOptometra();
    }*/
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> editarDatosOptometra(@PathVariable("id") int id, @RequestBody Map<String, Object> requestBody) {
        String nuevadireccion = (String) requestBody.get("nuevadireccion");
        String nuevocorreo = (String) requestBody.get("nuevocorreo");
        Long nuevotelefono = Long.parseLong((String) requestBody.get("nuevotelefono"));

        if (nuevadireccion == null || nuevocorreo == null) {
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
            int idlogin = (int) requestBody.get("idadmin");
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
            String codigorec = generarCodigoRecuperacion();
            System.out.println(codigorec + "codigo recuperacion que genero");
            String rescodigo = passwordEncoder.encode(codigorec);
            System.out.println(rescodigo + "codigo encriptada");
            usuario.setCodigorecuperacion(rescodigo);

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



            envioCorreoService.enviarCorreoRegistroOptometra(usuario.getCorreo(), "Registro exitoso", usuario.getCorreo(), pass,codigorec);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String fecha1 = dateFormat.format(new Date());
            Auditoria auditoria = new Auditoria();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(requestBody);
            auditoria.setInformacion(jsonString);
            auditoria.setAccion("Registro de Optometra");
            auditoria.setFecha(fecha1);
            auditoria.setIdusuario(idlogin);
            auditoriaServices.registrarAuditoria(auditoria);
            return ResponseEntity.ok().build(); // Registro exitoso


        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar usuario: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PutMapping("/cambiarEstado/{id}")
    public ResponseEntity<?> editarEstadoOptometra(@RequestBody Map<String, Object> requestBody) {
        int idOptometra = (int) requestBody.get("idoptometra");
        int idadmin = (int) requestBody.get("idadmin");
        boolean resultado = usuarioService.cambiarEstadoUsuario(idOptometra);

        if (resultado) {
            // Registrar la auditoría
            Auditoria auditoria = new Auditoria();
            auditoria.setInformacion(String.valueOf(resultado)); // Almacenar el estado como un String
            auditoria.setAccion("Cambiar estado del Optometra(activo e inactivo)");
            auditoria.setFecha(fecha1);
            auditoria.setIdusuario(idadmin); // Suponiendo que idlogin es el ID del usuario que realiza la acción
            auditoriaServices.registrarAuditoria(auditoria);

            return ResponseEntity.ok("El estado del optometra ha sido cambiado exitosamente");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo cambiar el estado del optometra");
        }
    }
    @GetMapping("/verificarCodigoRecuperacion")
    public ResponseEntity<?> verificarCodigoRecuperacion(@RequestParam String correo, @RequestParam String codigorecuperacion) {

        // Buscar en la base de datos el usuario con el correo proporcionado
        Usuario usuario = usuarioService.obtenerUsuarioPorCorreo(correo);
        System.out.println(usuario.getCorreo()+"Que correo trae ");

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el correo proporcionado no existe");
        }

        // Verificar si el código de recuperación proporcionado coincide con el almacenado en la base de datos
        if (passwordEncoder.matches(codigorecuperacion, usuario.getCodigorecuperacion())) {
            // Si coincide, devolver un mensaje de éxito y permitir al usuario agregar la nueva contraseña
            return ResponseEntity.ok().build();
        } else {
            // Si no coincide, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El código de recuperación proporcionado no es válido.");
        }
    }

    @PutMapping("/actualizarContrasena")
    public ResponseEntity<?> actualizarContraseña(@RequestBody Map<String, Object> requestBody) {
        String cedula = (String) requestBody.get("cedula");
        String nuevaContraseña = (String) requestBody.get("nuevacontrasena");
        // Buscar en la base de datos el usuario con el correo proporcionado
        Usuario usuario = usuarioService.obtenerUsuarioPorCorreo(cedula);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el correo proporcionado no existe");
        }

        // Actualizar la contraseña del usuario
        boolean actualizacionContraseñaExitosa = usuarioService.actualizarContraseña(usuario.getIdusuario(), nuevaContraseña);
        if (actualizacionContraseñaExitosa) {
            // Registrar la auditoría
            Auditoria auditoria = new Auditoria();
            auditoria.setInformacion("EL usuario "+ usuario.getNombre()+"Actualizo la contraseña"); // Almacenar el estado como un String
            auditoria.setAccion("Actualizar informacion");
            auditoria.setFecha(fecha1);
            auditoria.setIdusuario(usuario.getIdusuario()); // Suponiendo que idlogin es el ID del usuario que realiza la acción
            auditoriaServices.registrarAuditoria(auditoria);
            // Si la actualización de la contraseña es exitosa, devolver un mensaje de éxito
            return ResponseEntity.ok().build();
        } else {
            // Si ocurre un error al actualizar la contraseña, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la contraseña del usuario");
        }
    }





}

