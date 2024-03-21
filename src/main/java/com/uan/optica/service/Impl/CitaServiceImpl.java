package com.uan.optica.service.Impl;
import com.uan.optica.entities.Cita;
import com.uan.optica.entities.Usuario;
import com.uan.optica.repository.CitaRepository;
import com.uan.optica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    CitaRepository citaRepository;

    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public List<String> obtenerCita(String fecha) {
        return citaRepository.obtenerHoras(fecha);
    }

    @Override
    public Cita obtenerCitaporIdpaciente(int idpaciente) {
        Cita cita = citaRepository.getCita(String.valueOf(idpaciente));
        if (cita != null) {
            System.out.println("Cita encontrado: " + cita.getFecha()); // Imprime el nombre del usuario encontrado
        } else {
            System.out.println("Cita no encontrado para el ispaciente: " + idpaciente);
        }
        return cita;
    }
}
