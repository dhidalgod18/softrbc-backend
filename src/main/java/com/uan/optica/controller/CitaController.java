package com.uan.optica.controller;

import com.uan.optica.entities.Cita;
import com.uan.optica.entities.Usuario;
import com.uan.optica.service.CitaService;
import com.uan.optica.service.EnvioCorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.uan.optica.filtros.CodigoCitas.codigoCita;

@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    private CitaService citaService;
    @Autowired
    private EnvioCorreoService envioCorreoService;

    @GetMapping("/lista")
    public List<String> listahoras(@RequestParam String fecha) {
        List<String> horas = citaService.obtenerCita(fecha);
        for (String hora : horas) {
            System.out.println("hora: " + hora + ", fecha: " + fecha);
        }
        return horas;
    }

    @PostMapping("/nueva")
    public ResponseEntity<?> registrarCita(@RequestBody Map<String, Object> requestBody) {
        try {
            Cita cita = new Cita();
            String correo = (String) requestBody.get("correo");
            String fecha = (String) requestBody.get("fecha");
            cita.setFecha(fecha);
            cita.setNombre((String) requestBody.get("nombre"));
            cita.setTelefono((Long) requestBody.get("telefono"));
            cita.setIdpaciente((int) requestBody.get("idpaciente"));
            String codigo = codigoCita();
            cita.setCodigo(codigo);
            cita.setHora((String) requestBody.get("hora"));



            citaService.crearCita(cita);

            envioCorreoService.enviarCorreoVerificacionCita(correo,codigo,cita);


            // Devuelve una respuesta indicando que el registro de auditoría fue exitoso
            return ResponseEntity.ok("Registro de auditoría exitoso");
        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @GetMapping("/verificarCodigo")
    public ResponseEntity<?> verificarCodigo(@RequestParam String idpaciente, @RequestParam String codigo) {

        Cita cita = citaService.obtenerCitaporIdpaciente(Integer.parseInt(idpaciente));

        if (cita == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cita con el idpaciente proporcionado no existe");
        }

        // Verificar si la cita existe y el código coincide
        if (cita != null && codigo.equals(cita.getCodigo())) {
            // Si coincide, devolver la cita como parte de la respuesta
            return ResponseEntity.ok(cita);
        } else {
            // Si no coincide, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El código de recuperación proporcionado no es válido.");
        }
    }
        @DeleteMapping("/eliminar/{codigo}")
        public ResponseEntity<?> eliminarCita(@PathVariable String codigo) {
            boolean eliminado = citaService.eliminarCita(codigo);
            if (eliminado) {
                return ResponseEntity.ok("La cita fue eliminada correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cita con el código proporcionado no existe.");
            }
        }

}
