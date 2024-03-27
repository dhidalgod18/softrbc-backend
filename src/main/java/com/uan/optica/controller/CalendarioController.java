package com.uan.optica.controller;

import com.uan.optica.entities.Calendario;
import com.uan.optica.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calendario")
public class CalendarioController {
    @Autowired
    private CalendarioService calendarioService;

    @PostMapping("/nueva")
    public ResponseEntity<?> guardarNuevoCalendario(@RequestBody Map<String, Object> requestBody) {
        try {
            Calendario calendario = new Calendario();
            calendario.setDiasatencion((String) requestBody.get("diasatencion"));
            calendario.setDuracioncita((int)  requestBody.get("duracioncita"));
            calendario.setIdoptometra((int)  requestBody.get("idoptometra"));

            calendarioService.crearCalendario(calendario);

            return ResponseEntity.ok().build(); // Registro exitoso


        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar calendrio: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> editarCalendario(@PathVariable("id") int id, @RequestBody Map<String, Object> requestBody) {
        String nuevadiasatencion = (String) requestBody.get("nuevadiasatencion");
        int nuevaduracionLocal  = Integer.parseInt((String) requestBody.get("nuevaduracion"));

        if (nuevadiasatencion == null) {
            return ResponseEntity.badRequest().body("Falta información requerida");
        }

        boolean resultado = calendarioService.modificarDatosCalendario(id, nuevadiasatencion, nuevaduracionLocal);

        if (resultado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar los datos del calendario");
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

}


