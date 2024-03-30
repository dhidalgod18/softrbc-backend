package com.uan.optica.service;

import com.uan.optica.entities.Queratometria;
import org.springframework.stereotype.Service;

@Service
public interface QueratometriaService {
    void agregarQueratometria(Queratometria queratometria);
}
