package com.uan.optica.service;

import com.uan.optica.entities.Anamnesis;
import org.springframework.stereotype.Service;

@Service
public interface AnamnesisService {
    void agregarAnamnesis(Anamnesis anamnesis);
}
