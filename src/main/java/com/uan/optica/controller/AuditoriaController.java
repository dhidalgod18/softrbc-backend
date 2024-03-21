package com.uan.optica.controller;

import com.uan.optica.entities.Auditoria;
import com.uan.optica.service.AuditoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/auditoria")

public class AuditoriaController {

    @Autowired
    private AuditoriaServices auditoriaServices;

    @PostMapping("/nueva")
    public ResponseEntity<?> guardarNuevoCalendario(@RequestBody Map<String, Object> requestBody) {
        try {
            Auditoria auditoria = new Auditoria();
            String fecha = (String) requestBody.get("fecha");
            String fechasola = fecha.substring(0, 10); // Extraer solo la parte de la fecha
            LocalDate fechainformacion = LocalDate.parse(fechasola);
            auditoria.setFecha(fechainformacion);
            auditoria.setAccion((String) requestBody.get("accion"));
            auditoria.setIdusuario((int) requestBody.get("idusuario"));
            auditoria.setInformacion((String) requestBody.get("informacion"));

            auditoriaServices.registrarAuditoria(auditoria);

            // Devuelve una respuesta indicando que el registro de auditoría fue exitoso
            return ResponseEntity.ok("Registro de auditoría exitoso");
        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
