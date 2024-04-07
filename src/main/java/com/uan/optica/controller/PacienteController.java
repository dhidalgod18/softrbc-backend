package com.uan.optica.controller;

import com.uan.optica.entities.Paciente;
import com.uan.optica.entities.Usuario;
import com.uan.optica.entities.UsuarioOptometraDTO;
import com.uan.optica.entities.UsuarioPacienteDTO;
import com.uan.optica.service.EnvioCorreoService;
import com.uan.optica.service.PacienteService;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
            paciente.setFechanacimiento(fechaNacimiento);
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


}
