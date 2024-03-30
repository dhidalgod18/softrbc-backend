package com.uan.optica.service.Impl;

import com.uan.optica.entities.RxFinal;
import com.uan.optica.repository.RxFinalRepository;
import com.uan.optica.service.RxFinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RxFinalServiceImpl implements RxFinalService {
    @Autowired
    private RxFinalRepository rxFinalRepository;
    @Override
    public void agregarRxFinal(RxFinal rxFinal) {
        rxFinalRepository.save(rxFinal);

    }

    @Override
    public Optional<RxFinal> rxFinal(int idhistoria) {
        return rxFinalRepository.findById(idhistoria);
    }
}
