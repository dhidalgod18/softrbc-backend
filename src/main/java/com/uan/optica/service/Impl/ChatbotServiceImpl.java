package com.uan.optica.service.Impl;

import com.uan.optica.entities.Preguntas;
import com.uan.optica.repository.PreguntasRepository;
import com.uan.optica.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatbotServiceImpl implements ChatbotService {
    @Autowired
    private PreguntasRepository preguntasRepository;

    @Override
    public String obtenerRespuesta(int idpregunta) {
        Optional<Preguntas> preguntaEncontrada = preguntasRepository.findById(idpregunta);
        if (preguntaEncontrada.isPresent()) { // Verificar si el Optional contiene un valor
            return preguntaEncontrada.get().getRespuesta(); // Devolver la respuesta encontrada
        } else {
            return "Lo siento, no tengo una respuesta para esa pregunta.";
        }
    }
}
