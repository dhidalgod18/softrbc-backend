package com.uan.optica.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uan.optica.entities.Auditoria;
import com.uan.optica.entities.Calendario;
import com.uan.optica.service.AuditoriaServices;
import com.uan.optica.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/calendario")
public class CalendarioController {
    @Autowired
    private CalendarioService calendarioService;
    @Autowired
    AuditoriaServices auditoriaServices;
    private final ObjectMapper objectMapper = new ObjectMapper();



    @PostMapping("/nueva")
    public ResponseEntity<?> guardarNuevoCalendario(@RequestBody Map<String, Object> requestBody) {

        try {
            Calendario calendario = new Calendario();
            calendario.setDiasatencion((String) requestBody.get("diasatencion"));
            calendario.setDuracioncita((int)  requestBody.get("duracioncita"));
            calendario.setIdoptometra((int)  requestBody.get("idoptometra"));
            calendarioService.crearCalendario(calendario);
            Auditoria auditoria = new Auditoria();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(requestBody);
            auditoria.setInformacion(jsonString);
            auditoria.setAccion("Registro calendario");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String fecha1 = dateFormat.format(new Date());
            auditoria.setFecha(fecha1);
            auditoria.setIdusuario((int) requestBody.get("idadmin"));
            auditoriaServices.registrarAuditoria(auditoria);

            return ResponseEntity.ok().build(); // Registro exitoso


        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar calendrio: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> editarCalendario(@PathVariable("id") int id, @RequestBody Map<String, Object> requestBody) throws JsonProcessingException {
        String nuevadiasatencion = (String) requestBody.get("nuevadiasatencion");
        int nuevaduracionLocal = Integer.parseInt(requestBody.get("nuevaduracion").toString());

        if (nuevadiasatencion == null) {
            return ResponseEntity.badRequest().body("Falta información requerida");
        }

        Optional<Calendario> calendarioanteriorOpt = calendarioService.obtener(id);
        if (!calendarioanteriorOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Calendario calendarioanterior = calendarioanteriorOpt.get();

        Calendario calendario = new Calendario();
        calendario.setDiasatencion(calendarioanterior.getDiasatencion());
        calendario.setDuracioncita(calendarioanterior.getDuracioncita());
        calendario.setIdcalendario(calendarioanterior.getIdcalendario());
        calendario.setIdoptometra(calendarioanterior.getIdoptometra());

        boolean resultado = calendarioService.modificarDatosCalendario(id, nuevadiasatencion, nuevaduracionLocal);

        if (resultado) {
            try {
                // Crear un Map para almacenar la información anterior y actualizada del paciente
                Map<String, Object> informacionPaciente = new HashMap<>();
                informacionPaciente.put("anterior", calendario);
                informacionPaciente.put("actualizada", calendarioanterior);

                // Convertir el Map a JSON
                String jsonString = objectMapper.writeValueAsString(informacionPaciente);

                // Crear y configurar el objeto de auditoría
                Auditoria auditoria = new Auditoria();
                auditoria.setInformacion(jsonString);
                auditoria.setAccion("Actualizar datos del calendario");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                String fecha1 = dateFormat.format(new Date());
                auditoria.setFecha(fecha1); // Asegúrate de tener fecha1 definida adecuadamente
                auditoria.setIdusuario((int) requestBody.get("idadmin"));

                // Registrar la auditoría
                auditoriaServices.registrarAuditoria(auditoria);
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la información");
            }
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar los datos del calendario");
        }
    }

    @GetMapping("/duracioncita/{dia}")
    public ResponseEntity<?> obtenerDuracionCitaPorDia(@PathVariable("dia") String dia) {
        try {
            int duracionCita = calendarioService.duracioncita(dia);
            return ResponseEntity.ok(duracionCita);
        } catch (Exception e) {
            String errorMessage = "Error al intentar obtener duración de cita: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @GetMapping("/calendariooptometra")
    public ResponseEntity<List<String>> obtenerDiasCalendarioYOptometra() {
        try {
            List<String> diasCalendarioOptometra = calendarioService.diasCalendarioLaboral();
            return ResponseEntity.ok(diasCalendarioOptometra);
        } catch (Exception e) {
            String errorMessage = "Error al intentar obtener días del calendario y estado del optometra: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/calendariolista")
    public ResponseEntity<List<Calendario>> listaCalendarios() {
        List<Calendario> calendarios = calendarioService.calendariolista()  ;
        return ResponseEntity.ok(calendarios);
    }

}


