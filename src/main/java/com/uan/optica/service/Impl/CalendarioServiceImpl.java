package com.uan.optica.service.Impl;

import com.uan.optica.entities.Calendario;
import com.uan.optica.entities.Optometra;
import com.uan.optica.repository.CalendarioRepository;
import com.uan.optica.service.CalendarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarioServiceImpl implements CalendarioService {
    @Autowired
    private CalendarioRepository calendarioRepository;
    @Override
    public boolean crearCalendario(Calendario calendario) {
        Calendario calendarioGuardado = calendarioRepository.save(calendario);
        if (calendarioGuardado == null) {
            throw new RuntimeException("No se pudo guardar el calendario");
        }
        return true;
    }

    @Override
    public boolean modificarDatosCalendario(int idcalendario, String nuevadiasatencion, int nuevaduracion) {
        try {
            Optional<Calendario> optionalCalendario = calendarioRepository.findById(idcalendario);
            if (optionalCalendario.isPresent()) {
                Calendario calendario = optionalCalendario.get();
                calendario.setDiasatencion(nuevadiasatencion);
                calendario.setDuracioncita(nuevaduracion);
                calendarioRepository.save(calendario);
                return true;
            } else {
                return false; // El usuario no fue encontrado
            }
        } catch (NumberFormatException e) {
            // Manejo de la excepción si el formato del teléfono es inválido
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // Manejo de otras excepciones
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int duracioncita(String dia) {
        Integer duracionCita = calendarioRepository.obtenerDuracionCitaPorDia(dia);
        System.out.println(duracionCita+"Duracioncitaaaaaaaaaa");
        if (duracionCita != null) {
            return duracionCita;
        } else {
            throw new RuntimeException("No se encontró la duración de la cita para el día proporcionado");
        }
    }

    @Override
    public List<String> diasCalendarioLaboral() {
        List<Calendario> calendarios = calendarioRepository.findAll();
        List<String> diasLaborales = new ArrayList<>();

        for (Calendario calendario : calendarios) {
            Optometra optometra = calendarioRepository.findByUsuarioId(calendario.getIdoptometra());
            if (optometra != null && optometra.isActivo()) {
                diasLaborales.add(calendario.getDiasatencion());
            }
        }

        return diasLaborales;
    }

    @Override
    public List<Calendario> calendariolista() {
        List<Calendario> calendarios = calendarioRepository.findAll();
        return calendarios;
    }


}
