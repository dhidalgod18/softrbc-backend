package com.uan.optica.service.Impl;

import com.uan.optica.entities.*;
import com.uan.optica.repository.PacienteRepository;
import com.uan.optica.repository.UsuarioRepository;
import com.uan.optica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired

    private UsuarioRepository usuarioRepository;
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

    @Override
    public Paciente obtenerPacienteporId(int idpaciente) {
        Paciente paciente = pacienteRepository.findPacienteId(idpaciente);
        if (paciente != null) {
            System.out.println("Cita encontrado: " + paciente.getIdpaciente()); // Imprime el nombre del usuario encontrado
        } else {
            System.out.println("Paciente no encontrado por el idpaciente: " + idpaciente);
        }
        return paciente;

    }

    @Override
    public boolean guardarPaciente(Paciente paciente) {
        try {
            pacienteRepository.save(paciente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UsuarioPacienteDTO> obtenerUsuariosPacienteDTO(int id) {
        Usuario usuarios = pacienteRepository.findByUsuarioIdp(id);
        List<UsuarioPacienteDTO> usuarioPacienteDTO = new ArrayList<>();

        if (usuarios != null) {
            Paciente paciente = pacienteRepository.findByUsuarioId(id);
            if (paciente != null) {
                usuarioPacienteDTO.add(new UsuarioPacienteDTO(usuarios,paciente));
            }
        }

        return usuarioPacienteDTO;

    }

    @Override
    public boolean modificarDatosOptometra(UsuarioPacienteDTO pacienteDTO) {
        try {

            Usuario usuario = pacienteDTO.getUsuario();
            Paciente paciente = pacienteDTO.getPaciente();
            Usuario usuarioDB = pacienteRepository.findByUsuarioIdp(usuario.getIdusuario());
            //traer base de datos
            Paciente pacienteBD = pacienteRepository.findPacienteId(paciente.getIdpaciente());

            // Verificar si el paciente existe
            if (paciente == null) {
                return false;
            }


            // Modificar los datos del usuario
            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setApellido(usuario.getApellido());
            usuarioDB.setCorreo(usuario.getCorreo());
            usuarioDB.setDireccion(usuario.getDireccion());
            usuarioDB.setTelefono(usuario.getTelefono());
            usuarioDB.setCedula(usuario.getCedula());


            // Modificar los datos específicos del paciente
            pacienteBD.setOcupacion(paciente.getOcupacion());
            pacienteBD.setFechanacimiento(paciente.getFechanacimiento());
            pacienteBD.setGenero(paciente.getGenero());

            // Guardar los cambios en la base de datos
            pacienteRepository.save(pacienteBD);
            usuarioRepository.save(usuarioDB);


            return true; // La modificación fue exitosa
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Ocurrió un error durante la modificación
        }
    }

}
