package com.uan.optica.controller;

import com.uan.optica.entities.Calendario;
import com.uan.optica.entities.Preguntas;
import com.uan.optica.entities.UsuarioOptometraDTO;
import com.uan.optica.service.CalendarioService;
import com.uan.optica.service.PreguntasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/preguntas")
public class PreguntasController {
    @Autowired
    private PreguntasServices preguntasServices;

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

            return ResponseEntity.ok().build(); // Registro exitoso


        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar preguntas y respuestas: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarPreguntas(@PathVariable("id") int id, @RequestBody Map<String, Object> requestBody) {
        String nuevaPregunta = (String) requestBody.get("pregunta");
        String nuevaRespuesta = (String) requestBody.get("respuesta");

        System.out.println(nuevaPregunta);
        System.out.println(nuevaRespuesta);


        if (nuevaPregunta == null || nuevaRespuesta == null ) {
            return ResponseEntity.badRequest().body("Falta información requerida");
        }

        boolean resultado = preguntasServices.modificarDatosOptometra(id, nuevaPregunta, nuevaRespuesta);

        if (resultado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar los datos de la pregunta");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPregunta(@PathVariable("id") int id) {
        boolean eliminacionExitosa = preguntasServices.eliminarPregunta(id);
        System.out.println(id+"Quien tiene");
        if (eliminacionExitosa) {
            return ResponseEntity.ok().build(); // Eliminación exitosa
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la pregunta");
        }
    }

}
