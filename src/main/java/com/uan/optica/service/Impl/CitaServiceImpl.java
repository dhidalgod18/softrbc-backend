package com.uan.optica.service.Impl;
import com.uan.optica.entities.Cita;
import com.uan.optica.repository.CitaRepository;
import com.uan.optica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    CitaRepository citaRepository;

    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }
}
