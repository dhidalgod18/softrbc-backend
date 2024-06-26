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
    Cita citaid(int idcita);


    boolean eliminarCita(String codigo);

    List<Cita> obtenercitas(String fecha);
    List<Cita> obtenercitasEstadoFalse();


    void eliminar(int cita);
    Cita actualizarCita(Cita cita);




}
