package com.uan.optica.service.Impl;

import com.uan.optica.entities.Optometra;
import com.uan.optica.repository.OptometraRepository;
import com.uan.optica.service.OptometraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptometraServiceImpl implements OptometraService {
    @Autowired
    private OptometraRepository optometraRepository;
    public Optometra crearOptometra(Optometra optometra) {
        return optometraRepository.save(optometra);
    }
}
