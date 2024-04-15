package com.uan.optica.service;

import com.uan.optica.entities.Calendario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CalendarioService {
    boolean crearCalendario(Calendario calendario);
    boolean modificarDatosCalendario(int idcalendario, String nuevadiasatencion, int nuevaduracion);
    int duracioncita(String dia);
    List<String> diasCalendarioLaboral();
    List<Calendario> calendariolista();

    Optional<Calendario> obtener(int id);



}
