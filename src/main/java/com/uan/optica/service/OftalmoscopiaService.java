package com.uan.optica.service;

import com.uan.optica.entities.Oftalmoscopia;
import org.springframework.stereotype.Service;

@Service
public interface OftalmoscopiaService {

    void agregarOftalmoscopia(Oftalmoscopia oftalmoscopia);
}
