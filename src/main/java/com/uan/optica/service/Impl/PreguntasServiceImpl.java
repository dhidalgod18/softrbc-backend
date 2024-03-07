package com.uan.optica.service.Impl;

import com.uan.optica.entities.Calendario;
import com.uan.optica.entities.Preguntas;
import com.uan.optica.repository.PreguntasRepository;
import com.uan.optica.service.PreguntasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntasServiceImpl implements PreguntasServices {
    @Autowired
    private PreguntasRepository preguntasRepository;
    @Override
    public Preguntas crearPreguntasRespuestas(Preguntas preguntas) {
        return preguntasRepository.save(preguntas);
    }

    @Override
    public boolean modificarDatosOptometra(int idPregunta, String nuevaPregunta, String nuevaRespuesta) {
        try {
            Optional<Preguntas> optionalPreguntas = preguntasRepository.findById(idPregunta);
            if (optionalPreguntas.isPresent()) {
                Preguntas preguntas = optionalPreguntas.get();
                preguntas.setPregunta(nuevaPregunta);
                preguntas.setRespuesta(nuevaRespuesta);
                preguntasRepository.save(preguntas);
                return true;
            } else {
                return false; // El usuario no fue encontrado
            }
        } catch (NumberFormatException e) {
            // Manejo de la excepción si el formato del teléfono es inválido
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // Manejo de otras excepciones
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean eliminarPregunta(int idPregunta) {
        try {
            preguntasRepository.deleteById(idPregunta);
            return true; // La pregunta se eliminó correctamente
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Hubo un error al intentar eliminar la pregunta
        }
    }

    @Override
    public List<Preguntas> obtenerPreguntas() {
        return preguntasRepository.findAll();
    }
}
