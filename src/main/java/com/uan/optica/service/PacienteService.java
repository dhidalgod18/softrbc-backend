package com.uan.optica.service;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Paciente;
import com.uan.optica.entities.UsuarioOptometraDTO;
import com.uan.optica.entities.UsuarioPacienteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PacienteService {
    Paciente crearPaciente(Paciente paciente);
    int obtenerPaciente();
    Paciente obtenerPacienteporId(int idpaciente);

    boolean guardarPaciente(Paciente paciente);
    List<UsuarioPacienteDTO> obtenerUsuariosPacienteDTO(int id);
    boolean modificarDatosOptometra(UsuarioPacienteDTO pacienteDTO);


}
