package com.uan.optica.service;
import com.uan.optica.entities.Cita;
import org.springframework.stereotype.Service;

@Service
public interface CitaService {
    Cita crearCita(Cita cita);
}
