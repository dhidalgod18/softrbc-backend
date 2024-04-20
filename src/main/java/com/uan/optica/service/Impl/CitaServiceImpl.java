package com.uan.optica.service.Impl;
import com.uan.optica.entities.Cita;
import com.uan.optica.repository.CitaRepository;
import com.uan.optica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Cita cita = citaRepository.getCita(idpaciente);
        if (cita != null) {
            System.out.println("Cita encontrado: " + cita.getFecha()); // Imprime el nombre del usuario encontrado
        } else {
            System.out.println("Cita no encontrado para el ispaciente: " + idpaciente);
        }
        return cita;
    }

    @Override
    public Cita citaCodigo(String codigo) {
        return citaRepository.findByCodigo(codigo);
    }

    @Override
    public Cita citaid(int idcita) {
        return citaRepository.cita(idcita);
    }

    @Override
    public boolean eliminarCita(String codigo) {
        try {
            Cita cita = citaRepository.findByCodigo(codigo);

            if (cita != null) {
                cita.setEstado(false);
                citaRepository.save(cita);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Cita> obtenercitas(String fecha) {
        List<Cita> citas = citaRepository.obtenerCitasPorFecha(fecha);

        List<Cita> citasActivas = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.isEstado()) {
                citasActivas.add(cita);
            }
        }

        return citasActivas;
    }

    @Override
    public List<Cita> obtenercitasEstadoFalse() {
        List<Cita> citas = citaRepository.findAll();

        List<Cita> citasInactivas = new ArrayList<>();
        for (Cita cita : citas) {
            if (!cita.isEstado()) {
                citasInactivas.add(cita);
            }
        }

        return citasInactivas;

    }

    @Override
    public void eliminar(int cita) {
        citaRepository.deleteById(cita);
    }

    public Cita actualizarCita(Cita cita) {
        try {
            return citaRepository.save(cita);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


}
