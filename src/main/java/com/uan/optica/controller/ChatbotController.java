package com.uan.optica.controller;

import com.uan.optica.service.ChatbotService;
import com.uan.optica.service.PreguntasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Chatbot")
public class ChatbotController {
    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/respuesta")
    public String responderPregunta(@RequestBody int idPregunta) {
        // Llama al método obtenerRespuesta del servicio
        String respuesta = chatbotService.obtenerRespuesta(idPregunta);
        // Devuelve la respuesta al cliente
        return respuesta;
    }
}

