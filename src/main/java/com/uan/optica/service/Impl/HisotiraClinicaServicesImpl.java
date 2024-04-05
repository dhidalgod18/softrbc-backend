package com.uan.optica.service.Impl;

import com.uan.optica.entities.*;
import com.uan.optica.repository.HistoriaClinicaRepository;
import com.uan.optica.service.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HisotiraClinicaServicesImpl  implements HistoriaClinicaService {
    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public Historiaclinica crearHistoria(Historiaclinica historiaClinica) {
        return historiaClinicaRepository.save(historiaClinica);
    }


}