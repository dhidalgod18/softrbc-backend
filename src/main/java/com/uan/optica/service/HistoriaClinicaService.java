package com.uan.optica.service;

import com.uan.optica.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HistoriaClinicaService {
    Historiaclinica crearHistoria(Historiaclinica historiaClinica);

    List<HistoriaClinicaDTO> listaHistoria(Long cedula);

}
