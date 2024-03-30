package com.uan.optica.service;

import com.uan.optica.entities.Antecedentes;
import org.springframework.stereotype.Service;

@Service
public interface AntecedentesService {
    void agregarAntecedentes(Antecedentes antecedentes);
}
