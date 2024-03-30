package com.uan.optica.service;

import org.springframework.stereotype.Service;

@Service
public interface RxUsoService {
    void agregarRxEnUso(com.uan.optica.entities.RxUso rxUso);
}
