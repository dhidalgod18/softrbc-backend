package com.uan.optica.service;

import com.uan.optica.entities.Retinoscopia;
import org.springframework.stereotype.Service;

@Service
public interface RetinoscopiaService {
    void agregarRetinoscopia(Retinoscopia retinoscopia);
}
