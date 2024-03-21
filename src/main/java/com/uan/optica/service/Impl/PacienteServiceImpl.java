package com.uan.optica.service.Impl;

import com.uan.optica.entities.Paciente;
import com.uan.optica.entities.Usuario;
import com.uan.optica.repository.PacienteRepository;
import com.uan.optica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Override
    public Paciente crearPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public  int obtenerPaciente() {
        List<Usuario> usuarios = pacienteRepository.findByRol("ROLE_PACIENTE");
        int idpacientlogin = 0;

        for (Usuario usuario : usuarios) {
            Paciente paciente = pacienteRepository.findById(usuario.getIdusuario());
            if (paciente != null) {
                 idpacientlogin = paciente.getIdpaciente();
            }
        }

        return idpacientlogin;
    }

}
