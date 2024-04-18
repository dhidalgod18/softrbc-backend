package com.uan.optica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uan.optica.entities.*;
import com.uan.optica.service.AuditoriaServices;
import com.uan.optica.service.EnvioCorreoService;
import com.uan.optica.service.PacienteService;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.uan.optica.filtros.CodigoRecuperacion.generarCodigoRecuperacion;


@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EnvioCorreoService envioCorreoService;

    @Autowired
    AuditoriaServices auditoriaServices;


    private final ObjectMapper objectMapper = new ObjectMapper();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String fecha1 = dateFormat.format(new Date());


    @PostMapping("/nueva")
    public ResponseEntity<?> guardarNuevoPaciente(@RequestBody Map<String, Object> requestBody) {
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
            String pass = (String) usuarioMap.get("password");
            String passCodi = passwordEncoder.encode(pass);
            usuario.setPassword(passCodi);
            usuario.setCedula(Long.parseLong((String) usuarioMap.get("cedula")));
            String codigorec = generarCodigoRecuperacion();
            System.out.println(codigorec + "codigo recuperacion que genero");
            String rescodigo = passwordEncoder.encode(codigorec);
            System.out.println(rescodigo + "codigo encriptada");
            usuario.setCodigorecuperacion(rescodigo);
            usuario.setRol("ROLE_PACIENTE");

            System.out.println(usuario.toString()+"El usuario");

            // Crear el usuario
            usuarioService.crearPersona(usuario);

            // Extraer el objeto Paciente del cuerpo de la solicitud
            Map<String, Object> pacienteMap = (Map<String, Object>) requestBody.get("paciente");
            String ocupacion = (String) pacienteMap.get("ocupacion");
            String fechaNacimientoStr = (String) pacienteMap.get("fechanacimiento");
            String fechaNacimientoDateOnly = fechaNacimientoStr.substring(0, 10); // Extraer solo la parte de la fecha
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoDateOnly);
            String genero = (String) pacienteMap.get("genero");
            Paciente paciente = new Paciente();
            paciente.setOcupacion(ocupacion);
            paciente.setFechanacimiento(fechaNacimientoDateOnly);
            paciente.setGenero(genero);
            paciente.setIdusuario(usuario.getIdusuario());
            boolean aceptar = (Boolean) pacienteMap.get("aceptarterminos");
            paciente.setAceptarterminos(aceptar);
            paciente.setIdhistoriaclinica(null);
            String acompañante = (String) requestBody.get("nombreacompañante");
            if (acompañante != null){
                paciente.setNombreacompañante((String) requestBody.get("nombreacompañante"));
            }else {
                paciente.setNombreacompañante(null);
            }

            System.out.println(paciente.toString()+"Paciente");
            pacienteService.crearPaciente(paciente);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String fecha1 = dateFormat.format(new Date());
            Auditoria auditoria = new Auditoria();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(requestBody);
            auditoria.setInformacion(jsonString);
            auditoria.setAccion("Registro Paciente");
            auditoria.setFecha(fecha1);
            auditoria.setIdusuario(paciente.getIdpaciente());
            auditoriaServices.registrarAuditoria(auditoria);


            return ResponseEntity.ok().build(); // Registro exitoso


        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar usuario: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @GetMapping("/pacienteEncontrado/{idpaciente}")
    public ResponseEntity<List<UsuarioPacienteDTO>>pacientePorId(@PathVariable("idpaciente") int idpaciente) {
        Paciente paciente = pacienteService.obtenerPacienteporId(idpaciente);
        List<UsuarioPacienteDTO> usuarioPacienteDTOS =pacienteService.obtenerUsuariosPacienteDTO(paciente.getIdusuario());
        return ResponseEntity.ok(usuarioPacienteDTOS);

    }
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarPaciente(@RequestBody Map<String, Object> requestBody) {

        try {
            int idOptometra = (int) requestBody.get("idoptometra");
            UsuarioPacienteDTO usuarioPacienteDTO = new UsuarioPacienteDTO();

            Map<String, Object> usuarioMap = (Map<String, Object>) requestBody.get("usuario");
            Usuario usuario = new Usuario();
            usuario.setIdusuario((int) usuarioMap.get("idusuario"));
            usuario.setNombre((String) usuarioMap.get("nombre"));
            usuario.setApellido((String) usuarioMap.get("apellido"));
            usuario.setCorreo((String) usuarioMap.get("correo"));
            usuario.setDireccion((String) usuarioMap.get("direccion"));
            usuario.setTelefono(Long.parseLong(usuarioMap.get("telefono").toString()));
            usuario.setCedula(Long.parseLong(usuarioMap.get("cedula").toString()));

            Map<String, Object> pacienteMap = (Map<String, Object>) requestBody.get("paciente");
            int idpaciente = (int) pacienteMap.get("idpaciente");
            String ocupacion = (String) pacienteMap.get("ocupacion");
            String fechaNacimientoStr = (String) pacienteMap.get("fechanacimiento");
            String fechaNacimientoDateOnly = fechaNacimientoStr.substring(0, 10); // Extraer solo la parte de la fecha
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoDateOnly);
            String genero = (String) pacienteMap.get("genero");
            Paciente paciente = new Paciente();
            paciente.setIdpaciente(idpaciente);
            paciente.setOcupacion(ocupacion);
            paciente.setFechanacimiento(fechaNacimientoDateOnly);
            paciente.setGenero(genero);
            usuarioPacienteDTO.setUsuario(usuario);
            usuarioPacienteDTO.setPaciente(paciente);

            Usuario usuarioanterior = usuarioService.obtener(usuarioPacienteDTO.getUsuario().getIdusuario());

            Usuario usuariocopia = new Usuario();
            usuariocopia.setIdusuario(usuarioanterior.getIdusuario());
            usuariocopia.setNombre(usuarioanterior.getNombre());
            usuariocopia.setApellido(usuarioanterior.getApellido());
            usuariocopia.setCorreo(usuarioanterior.getCorreo());
            usuariocopia.setDireccion(usuarioanterior.getDireccion());
            usuariocopia.setTelefono(usuarioanterior.getTelefono());
            usuariocopia.setCedula(usuarioanterior.getCedula());

            boolean resultado = pacienteService.modificarDatosOptometra(usuarioPacienteDTO);

            if (resultado) {

                Map<String, Object> informacionPaciente = new HashMap<>();
                informacionPaciente.put("anterior", usuariocopia);
                informacionPaciente.put("actualizada", usuarioPacienteDTO);

                String jsonString = objectMapper.writeValueAsString(informacionPaciente);

                Auditoria auditoria = new Auditoria();
                auditoria.setInformacion(jsonString);
                auditoria.setAccion("Actualizar datos del paciente");
                auditoria.setFecha(fecha1);
                auditoria.setIdusuario(idOptometra);
                // Registrar la auditoría
                auditoriaServices.registrarAuditoria(auditoria);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar los datos del paciente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
        }
    }


}
