package com.uan.optica.service.Impl;

import com.uan.optica.entities.Anamnesis;
import com.uan.optica.repository.AnamnesisRepository;
import com.uan.optica.service.AnamnesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnamnesisServiceImpl implements AnamnesisService {
    @Autowired
    private AnamnesisRepository anamnesisRepository;
    @Override
    public void agregarAnamnesis(Anamnesis anamnesis) {
        anamnesisRepository.save(anamnesis);

    }
}
