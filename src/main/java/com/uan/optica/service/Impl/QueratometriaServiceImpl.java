package com.uan.optica.service.Impl;

import com.uan.optica.entities.Queratometria;
import com.uan.optica.repository.QueratometriaRepository;
import com.uan.optica.service.QueratometriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueratometriaServiceImpl implements QueratometriaService {
    @Autowired
    private QueratometriaRepository queratometriaRepository;
    @Override
    public void agregarQueratometria(Queratometria queratometria) {
        queratometriaRepository.save(queratometria);
    }
}
