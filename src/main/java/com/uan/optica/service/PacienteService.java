package com.uan.optica.service;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Paciente;
import org.springframework.stereotype.Service;

@Service
public interface PacienteService {
    Paciente crearPaciente(Paciente paciente);

}
