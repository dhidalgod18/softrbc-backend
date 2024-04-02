package com.uan.optica.service;

import com.uan.optica.entities.RxUso;
import org.springframework.stereotype.Service;

@Service
public interface RxUsoService {
    void agregarRxEnUso(RxUso rxUso);
}
