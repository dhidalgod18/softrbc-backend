package com.uan.optica.service.Impl;

import com.uan.optica.entities.Auditoria;
import com.uan.optica.repository.AuditoriaRepository;
import com.uan.optica.service.AuditoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriaServicesImpl implements AuditoriaServices {
    @Autowired
    AuditoriaRepository auditoriaRepository;
    @Override
    public boolean registrarAuditoria(Auditoria auditoria) {
       Auditoria auditoria1 =  auditoriaRepository.save(auditoria);
       if (auditoria1 == null){
           throw new RuntimeException("No se pudo guardar el registro");
       }
        return true;
    }

    @Override
    public List<Auditoria> auditorias() {
        return auditoriaRepository.findAll();
    }
}
