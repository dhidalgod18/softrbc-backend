package com.uan.optica.service;

import com.uan.optica.entities.Preguntas;
import com.uan.optica.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PreguntasServices {
    Preguntas crearPreguntasRespuestas(Preguntas preguntas);
    boolean modificarDatosOptometra(int idPregunta, String nuevaPregunta, String nuevaRespuesta);
    boolean eliminarPregunta(int idPregunta);
    List<Preguntas> obtenerPreguntas();
    Preguntas buscar(int id);

}
