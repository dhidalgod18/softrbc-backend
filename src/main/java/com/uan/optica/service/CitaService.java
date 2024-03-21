package com.uan.optica.service;
import com.uan.optica.entities.Cita;
import com.uan.optica.entities.Preguntas;
import com.uan.optica.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CitaService {
    Cita crearCita(Cita cita);
    List<String> obtenerCita(String hora);

    Cita obtenerCitaporIdpaciente(int idpaciente);


}
