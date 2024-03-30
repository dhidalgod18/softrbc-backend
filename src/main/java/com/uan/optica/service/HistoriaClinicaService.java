package com.uan.optica.service;

import com.uan.optica.entities.*;
import org.springframework.stereotype.Service;

@Service
public interface HistoriaClinicaService {
    HistoriaClinica crearHistoria(HistoriaClinica historiaClinica);

}
