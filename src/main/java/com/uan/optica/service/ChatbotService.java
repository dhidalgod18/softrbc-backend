package com.uan.optica.service;

import org.springframework.stereotype.Service;

@Service
public interface ChatbotService {
    String obtenerRespuesta(int idpregunta);
}
