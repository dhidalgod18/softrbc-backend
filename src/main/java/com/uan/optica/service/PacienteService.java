package com.uan.optica.service;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Paciente;
import com.uan.optica.entities.UsuarioOptometraDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PacienteService {
    Paciente crearPaciente(Paciente paciente);
    int obtenerPaciente();
    Paciente obtenerPacienteporId(int idpaciente);

    boolean guardarPaciente(Paciente paciente);


}
