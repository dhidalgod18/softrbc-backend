package com.uan.optica.service;

import com.uan.optica.entities.Calendario;
import org.springframework.stereotype.Service;

@Service
public interface CalendarioService {
    boolean crearCalendario(Calendario calendario);
    boolean modificarDatosCalendario(int idcalendario, String nuevadiasatencion, int nuevaduracion);
    int duracioncita(String dia);


}
