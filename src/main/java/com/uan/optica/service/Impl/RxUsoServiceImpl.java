package com.uan.optica.service.Impl;

import com.uan.optica.entities.RxUso;
import com.uan.optica.repository.RxUsoRepository;
import com.uan.optica.service.RxUsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RxUsoServiceImpl implements RxUsoService {
    @Autowired
    private RxUsoRepository rxUsoRepository;
    @Override
    public void agregarRxEnUso(RxUso rxUso) {
        rxUsoRepository.save(rxUso);

    }
}
