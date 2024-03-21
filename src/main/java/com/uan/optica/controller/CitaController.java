package com.uan.optica.controller;

import com.uan.optica.entities.Auditoria;
import com.uan.optica.entities.Cita;
import com.uan.optica.filtros.CodigoCitas;
import com.uan.optica.service.AuditoriaServices;
import com.uan.optica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

import static com.uan.optica.filtros.CodigoCitas.codigoCita;

@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    private CitaService citaService;


    @PostMapping("/nueva")
    public ResponseEntity<?> registrarCita(@RequestBody Map<String, Object> requestBody) {
        try {
            Cita cita = new Cita();
            String fecha = (String) requestBody.get("fecha");
            cita.setFecha(fecha);
            cita.setNombre((String) requestBody.get("nombre"));
            cita.setTelefono((Long) requestBody.get("telefono"));
            cita.setIdpaciente((int) requestBody.get("idpaciente"));
            String codigo = codigoCita();
            cita.setCodigo(codigo);
            cita.setHora((String) requestBody.get("hora"));



            citaService.crearCita(cita);



            // Devuelve una respuesta indicando que el registro de auditoría fue exitoso
            return ResponseEntity.ok("Registro de auditoría exitoso");
        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
