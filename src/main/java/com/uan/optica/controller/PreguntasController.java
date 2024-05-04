package com.uan.optica.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uan.optica.entities.Auditoria;
import com.uan.optica.entities.Preguntas;
import com.uan.optica.service.AuditoriaServices;
import com.uan.optica.service.PreguntasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/preguntas")
public class PreguntasController {
    @Autowired
    private PreguntasServices preguntasServices;
    @Autowired
    AuditoriaServices auditoriaServices;
    private final ObjectMapper objectMapper = new ObjectMapper();



    @GetMapping("/listaPreguntas")
    public List<Preguntas> listaOptometras() {
        return preguntasServices.obtenerPreguntas();
    }

    @PostMapping("/nueva")
    public ResponseEntity<?> guardarPreguntasRespuestas(@RequestBody Map<String, Object> requestBody) {
        try {
            Preguntas preguntas = new Preguntas();
            preguntas.setPregunta((String) requestBody.get("pregunta"));
            preguntas.setRespuesta((String) requestBody.get("respuesta"));
            preguntasServices.crearPreguntasRespuestas(preguntas);
            Auditoria auditoria = new Auditoria();
            String jsonString = objectMapper.writeValueAsString(requestBody);
            auditoria.setInformacion(jsonString);
            auditoria.setAccion("Registro preguntas chatbot");
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
            String fechaFormateada = fechaHoraActual.format(formatter);
            auditoria.setFecha(fechaFormateada);
            auditoria.setIdusuario((int) requestBody.get("idadmin")); // Suponiendo que idlogin es el ID del usuario que realiza la acción
            auditoriaServices.registrarAuditoria(auditoria);

            return ResponseEntity.ok().build(); // Registro exitoso


        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar preguntas y respuestas: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarPreguntas(@PathVariable("id") int id, @RequestBody Map<String, Object> requestBody) throws JsonProcessingException {
        String nuevaPregunta = (String) requestBody.get("pregunta");
        String nuevaRespuesta = (String) requestBody.get("respuesta");
        Preguntas preguntas = preguntasServices.buscar(id);

        Preguntas preguntasanterior = new Preguntas();
        preguntasanterior.setIdpregunta(preguntas.getIdpregunta());
        preguntasanterior.setPregunta(preguntas.getPregunta());
        preguntasanterior.setRespuesta(preguntas.getRespuesta());


        if (nuevaPregunta == null || nuevaRespuesta == null ) {
            return ResponseEntity.badRequest().body("Falta información requerida");
        }

        boolean resultado = preguntasServices.modificarDatosOptometra(id, nuevaPregunta, nuevaRespuesta);

        if (resultado) {
            // Convertir la nueva información a JSON
            Map<String, Object> informacionOptometra = new HashMap<>();
            informacionOptometra.put("anterior", preguntasanterior);
            informacionOptometra.put("Actualizada", preguntas);

            String jsonString = objectMapper.writeValueAsString(informacionOptometra);

            // Crear y configurar el objeto de auditoría
            Auditoria auditoria = new Auditoria();
            auditoria.setInformacion(jsonString);
            auditoria.setAccion("Actualizar datos de preguntas");
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
            String fechaFormateada = fechaHoraActual.format(formatter);
            auditoria.setFecha(fechaFormateada);
            int idAdmin = (int) requestBody.get("idadmin");
            auditoria.setIdusuario(idAdmin);
            auditoriaServices.registrarAuditoria(auditoria);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar los datos de la pregunta");
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarPregunta(@RequestParam int id, @RequestParam int idadmin) throws JsonProcessingException {
        Preguntas preguntas = preguntasServices.buscar(id);
        boolean eliminacionExitosa = preguntasServices.eliminarPregunta(id);
        if (eliminacionExitosa) {
            Map<String, Object> informacionOptometra = new HashMap<>();
            informacionOptometra.put("Eliminar pregunta", preguntas);
            String jsonString = objectMapper.writeValueAsString(informacionOptometra);


            Auditoria auditoria = new Auditoria();
            auditoria.setInformacion(jsonString); // Almacenar el estado como un String
            auditoria.setAccion("eliminar pregunta");
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
            String fechaFormateada = fechaHoraActual.format(formatter);
            auditoria.setFecha(fechaFormateada);
            auditoria.setIdusuario(idadmin); // Suponiendo que idlogin es el ID del usuario que realiza la acción
            auditoriaServices.registrarAuditoria(auditoria);
            return ResponseEntity.ok().build(); // Eliminación exitosa
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la pregunta");
        }
    }

}
