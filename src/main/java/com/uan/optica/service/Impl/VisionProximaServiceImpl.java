package com.uan.optica.service.Impl;

import com.uan.optica.entities.VisionProxima;
import com.uan.optica.repository.VisionProximaRepository;
import com.uan.optica.service.VisionProximaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisionProximaServiceImpl implements VisionProximaService {
    @Autowired
    private VisionProximaRepository visionProximaRepository;
    @Override
    public void agregarVisionProxima(VisionProxima visionProxima) {
        visionProximaRepository.save(visionProxima);
    }
}
