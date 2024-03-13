package com.uan.optica.service.Impl;

import com.uan.optica.entities.Paciente;
import com.uan.optica.repository.OptometraRepository;
import com.uan.optica.repository.PacienteRepository;
import com.uan.optica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Override
    public Paciente crearPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
}
