package com.uan.optica.service;
import com.uan.optica.entities.Cita;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CitaService {
    Cita crearCita(Cita cita);
    List<String> obtenerCita(String hora);

    Cita obtenerCitaporIdpaciente(int idpaciente);

    Cita citaCodigo(String codigo);


    boolean eliminarCita(String codigo);

    List<Cita> obtenercitas(String fecha);

    void eliminar(int cita);


}
