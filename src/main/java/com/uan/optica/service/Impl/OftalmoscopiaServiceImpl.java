package com.uan.optica.service.Impl;

import com.uan.optica.entities.Oftalmoscopia;
import com.uan.optica.repository.OftalmoscopiaRepository;
import com.uan.optica.service.OftalmoscopiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OftalmoscopiaServiceImpl implements OftalmoscopiaService {
    @Autowired
    private OftalmoscopiaRepository oftalmoscopiaRepository;
    @Override
    public void agregarOftalmoscopia(Oftalmoscopia oftalmoscopia) {
        oftalmoscopiaRepository.save(oftalmoscopia);
    }
}
