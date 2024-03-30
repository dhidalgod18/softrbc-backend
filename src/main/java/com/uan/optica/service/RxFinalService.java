package com.uan.optica.service;

import com.uan.optica.entities.RxFinal;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RxFinalService {
    void agregarRxFinal(RxFinal rxFinal);
    Optional<RxFinal> rxFinal(int idhistoria);

}
