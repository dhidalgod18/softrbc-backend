package com.uan.optica.service.Impl;

import com.uan.optica.entities.Motilidad;
import com.uan.optica.repository.MotilidadRepository;
import com.uan.optica.service.MotilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotilidadServiceImpl implements MotilidadService {
    @Autowired
    private MotilidadRepository motilidadRepository;
    @Override
    public void agregarMotilidad(Motilidad motilidad) {
        motilidadRepository.save(motilidad);

    }
}
