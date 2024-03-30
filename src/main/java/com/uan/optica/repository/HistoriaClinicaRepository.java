package com.uan.optica.repository;

import com.uan.optica.entities.Cita;
import com.uan.optica.entities.HistoriaClinica;
import com.uan.optica.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaClinicaRepository  extends JpaRepository<HistoriaClinica, Integer> {

}
