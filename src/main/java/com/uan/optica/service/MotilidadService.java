package com.uan.optica.service;

import com.uan.optica.entities.Motilidad;
import org.springframework.stereotype.Service;

@Service
public interface MotilidadService {
    void agregarMotilidad(Motilidad motilidad);
}
