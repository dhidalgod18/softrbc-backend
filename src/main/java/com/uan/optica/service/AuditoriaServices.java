package com.uan.optica.service;

import com.uan.optica.entities.Auditoria;
import org.springframework.stereotype.Service;

@Service
public interface AuditoriaServices {
    boolean registrarAuditoria(Auditoria auditoria);
}
