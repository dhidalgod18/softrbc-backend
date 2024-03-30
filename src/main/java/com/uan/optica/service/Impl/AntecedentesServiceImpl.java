package com.uan.optica.service.Impl;

import com.uan.optica.entities.Antecedentes;
import com.uan.optica.repository.AntecedentesRepository;
import com.uan.optica.service.AntecedentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AntecedentesServiceImpl implements AntecedentesService {
    @Autowired
    private AntecedentesRepository antecedentesRepository;
    @Override
    public void agregarAntecedentes(Antecedentes antecedentes) {
        antecedentesRepository.save(antecedentes);
    }
}
