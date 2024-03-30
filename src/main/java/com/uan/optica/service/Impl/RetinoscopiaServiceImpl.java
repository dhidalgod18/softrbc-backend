package com.uan.optica.service.Impl;

import com.uan.optica.entities.Retinoscopia;
import com.uan.optica.repository.RetinoscopiaRepository;
import com.uan.optica.service.RetinoscopiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetinoscopiaServiceImpl implements RetinoscopiaService {
    @Autowired
    private RetinoscopiaRepository retinoscopiaRepository;
    @Override
    public void agregarRetinoscopia(Retinoscopia retinoscopia) {
        retinoscopiaRepository.save(retinoscopia);

    }
}
