package com.uan.optica.service.Impl;

import com.uan.optica.entities.VisionLejana;
import com.uan.optica.repository.VisionLejanaRepository;
import com.uan.optica.service.VisionLejanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisionLejanaServiceImpl implements VisionLejanaService {
    @Autowired
    private VisionLejanaRepository visionLejanaRepository;
    @Override
    public void agregarVisionLejana(VisionLejana visionLejana) {
        visionLejanaRepository.save(visionLejana);
    }
}
