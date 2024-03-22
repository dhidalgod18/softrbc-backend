package com.uan.optica.service;

import com.uan.optica.entities.Calendario;
import com.uan.optica.entities.Cita;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalendarioService {
    boolean crearCalendario(Calendario calendario);
    boolean modificarDatosCalendario(int idcalendario, String nuevadiasatencion, int nuevaduracion);
    int duracioncita(String dia);
    List<Cita> obtenercitas(String fecha);
    void generarPdfCitasPorFecha(String fecha);



}
