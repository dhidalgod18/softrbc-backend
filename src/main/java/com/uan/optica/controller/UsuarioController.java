package com.uan.optica.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.*;

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
    private final ObjectMapper objectMapper = new ObjectMapper();






    @GetMapping("/listaOptometras")
    public ResponseEntity<List<UsuarioOptometraDTO>> obtenerUsuariosOptometra() {
        List<UsuarioOptometraDTO> usuariosOptometraDTO = usuarioService.obtenerUsuariosOptometraDTO()  ;
        return ResponseEntity.ok(usuariosOptometraDTO);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> editarDatosOptometra(@PathVariable("id") int id, @RequestBody Map<String, Object> requestBody) throws JsonProcessingException {

        String nuevadireccion = (String) requestBody.get("nuevadireccion");
        String nuevocorreo = (String) requestBody.get("nuevocorreo");
        Long nuevotelefono = Long.parseLong(requestBody.get("nuevotelefono").toString());

        if (nuevadireccion == null || nuevocorreo == null) {
            return ResponseEntity.badRequest().body("Falta información requerida");
        }

        Usuario optometraAnterior = usuarioService.obtener(id);
        Usuario optometraAnteriorCopia = new Usuario();
        optometraAnteriorCopia.setIdusuario(optometraAnterior.getIdusuario());
        optometraAnteriorCopia.setNombre(optometraAnterior.getNombre());
        optometraAnteriorCopia.setApellido(optometraAnterior.getApellido());
        optometraAnteriorCopia.setPassword(optometraAnterior.getPassword());
        optometraAnteriorCopia.setRol(optometraAnterior.getRol());
        optometraAnteriorCopia.setCedula(optometraAnterior.getCedula());
        optometraAnteriorCopia.setCodigorecuperacion(optometraAnterior.getCodigorecuperacion());
        optometraAnteriorCopia.setDireccion(optometraAnterior.getDireccion());
        optometraAnteriorCopia.setCorreo(optometraAnterior.getCorreo());
        optometraAnteriorCopia.setTelefono(optometraAnterior.getTelefono());
        boolean resultado = usuarioService.modificarDatosOptometra(id, nuevadireccion, nuevocorreo, nuevotelefono);

        if (resultado) {
            // Convertir la nueva información a JSON
            Map<String, Object> informacionOptometra = new HashMap<>();
            informacionOptometra.put("anterior", optometraAnteriorCopia);
            informacionOptometra.put("Actualizada", optometraAnterior);

            String jsonString = objectMapper.writeValueAsString(informacionOptometra);

            // Crear y configurar el objeto de auditoría
            Auditoria auditoria = new Auditoria();
            auditoria.setInformacion(jsonString);
            auditoria.setAccion("Actualizar datos del optometra");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String fecha1 = dateFormat.format(new Date());
            auditoria.setFecha(fecha1);
            int idAdmin = (int) requestBody.get("idadmin");
            auditoria.setIdusuario(idAdmin);

            // Registrar la auditoría
            auditoriaServices.registrarAuditoria(auditoria);

            return ResponseEntity.ok("Datos del optometra actualizados exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar los datos del optometra");
        }
    }
    @PostMapping("/nueva")
    public ResponseEntity<?> guardarNuevaUsuario(@RequestBody Map<String, Object> requestBody) {
        try {
            int idlogin = (int) requestBody.get("idadmin");
            Map<String, Object> usuarioMap = (Map<String, Object>) requestBody.get("usuario");
            Usuario usuario = new Usuario();
            usuario.setNombre((String) usuarioMap.get("nombre"));
            usuario.setApellido((String) usuarioMap.get("apellido"));
            usuario.setCorreo((String) usuarioMap.get("correo"));
            usuario.setDireccion((String) usuarioMap.get("direccion"));
            usuario.setTelefono(Long.parseLong((String) usuarioMap.get("telefono")));
            String pass = generarPassword();
            String respass = passwordEncoder.encode(pass);
            usuario.setPassword(respass);
            usuario.setCedula(Long.parseLong((String) usuarioMap.get("cedula")));
            usuario.setRol("ROLE_OPTOMETRA");
            String codigorec = generarCodigoRecuperacion();
            String rescodigo = passwordEncoder.encode(codigorec);
            usuario.setCodigorecuperacion(rescodigo);

            Usuario usuarioexiste = usuarioService.obtenerUsuarioCedula(usuario.getCedula());

            if (usuarioexiste != null){

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario registrado con el número de cédula proporcionado");

            }

            usuarioService.crearPersona(usuario);

            Optometra optometra = new Optometra();

            optometra.setIdusuario(usuario.getIdusuario()); // Asignar el ID del usuario

            optometra.setActivo(true);
            optometraService.crearOptometra(optometra);
            int idUsuarioAsignado = optometra.getIdusuario();



            envioCorreoService.enviarCorreoRegistroOptometra(usuario.getCorreo(), "Registro exitoso", usuario.getCedula().toString(), pass,codigorec);

            Auditoria auditoria = new Auditoria();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(requestBody);
            auditoria.setInformacion(jsonString);
            auditoria.setAccion("Registro de Optometra");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String fecha1 = dateFormat.format(new Date());
            auditoria.setFecha(fecha1);
            auditoria.setIdusuario(idlogin);
            auditoriaServices.registrarAuditoria(auditoria);
            return ResponseEntity.ok().build(); // Registro exitoso


        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar usuario: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @PostMapping("/nuevaAdmin")
    public ResponseEntity<?> admin() {
        try {

            Usuario usuario = new Usuario();
            usuario.setNombre("Carlos Enrique");
            usuario.setApellido("Hidalgo Chaverra");
            usuario.setCorreo("chidalgodevia28@gmail.com");
            usuario.setDireccion("Urbanizacion el encanto cs 10");
            usuario.setTelefono(Long.parseLong((String) ("3133507814")));
            String pass = "Colombia24*";
            String respass = passwordEncoder.encode(pass);
            usuario.setPassword(respass);
            usuario.setCedula(Long.parseLong((String) ("71184737")));
            usuario.setRol("ROLE_ADMIN");
            String codigorec = generarCodigoRecuperacion();
            String rescodigo = passwordEncoder.encode(codigorec);
            usuario.setCodigorecuperacion(rescodigo);


            usuarioService.crearPersona(usuario);
            envioCorreoService.enviarCorreoRegistroOptometra(usuario.getCorreo(), "Registro exitoso", usuario.getCedula().toString(), pass,codigorec);

            return ResponseEntity.ok().build(); // Registro exitoso


        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar usuario: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }



    @PutMapping("/cambiarEstado")
    public ResponseEntity<?> editarEstadoOptometra(@RequestBody Map<String, Object> requestBody) throws JsonProcessingException {

        int id = (int) requestBody.get("idusuario");
        int idadmin = (int) requestBody.get("idadmin");
        Usuario resultadanterior = usuarioService.obtener(id);
        Optometra op = optometraService.obtener(resultadanterior.getIdusuario());
        Optometra optometraAnterior = new Optometra();
        optometraAnterior.setIdusuario(op.getIdusuario());
        optometraAnterior.setIdoptometra(op.getIdoptometra());
        optometraAnterior.setActivo(op.isActivo());
        boolean resultado = usuarioService.cambiarEstadoUsuario(id);

        if (resultado) {
            Map<String, Object> estado = new HashMap<>();
            estado.put("anterior", optometraAnterior);
            estado.put("Actualizada", op);

            String jsonString = objectMapper.writeValueAsString(estado);
            // Registrar la auditoría
            Auditoria auditoria = new Auditoria();
            auditoria.setInformacion(String.valueOf(jsonString)); // Almacenar el estado como un String
            auditoria.setAccion("Cambiar estado del Optometra");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String fecha1 = dateFormat.format(new Date());
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

        Usuario usuario = usuarioService.obtenerUsuarioPorCorreo(correo);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el correo proporcionado no existe");
        }

        if (passwordEncoder.matches(codigorecuperacion, usuario.getCodigorecuperacion())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El código de recuperación proporcionado no es válido.");
        }
    }

    @PutMapping("/actualizarContrasena")
    public ResponseEntity<?> actualizarContrasena(@RequestBody Map<String, Object> requestBody) throws JsonProcessingException {

        String cedula = (String) requestBody.get("cedula");
        String nuevaContrasena = (String) requestBody.get("nuevacontrasena");
        Usuario usuario = usuarioService.obtenerUsuarioPorCorreo(cedula);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el correo proporcionado no existe");
        }

        boolean actualizacionContrasenaExitosa = usuarioService.actualizarContrasena(usuario.getIdusuario(), nuevaContrasena);
        if (actualizacionContrasenaExitosa) {
            Map<String, Object> estado = new HashMap<>();
            estado.put("Contraseña Actualizada","EL usuario " + usuario.getNombre() + " Actualizo la contraseña");
            String jsonString = objectMapper.writeValueAsString(estado);

            // Registrar la auditoría
            Auditoria auditoria = new Auditoria();
            auditoria.setInformacion(jsonString); // Almacenar el estado como un String
            auditoria.setAccion("Actualizar contraseña");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String fecha1 = dateFormat.format(new Date());
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

