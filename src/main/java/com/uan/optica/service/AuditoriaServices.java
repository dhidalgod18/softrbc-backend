package com.uan.optica.service;

import com.uan.optica.entities.Auditoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuditoriaServices {
    boolean registrarAuditoria(Auditoria auditoria);
    List<Auditoria> auditorias();
}
