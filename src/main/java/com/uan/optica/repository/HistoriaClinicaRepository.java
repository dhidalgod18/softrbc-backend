package com.uan.optica.repository;

import com.uan.optica.entities.Historiaclinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaClinicaRepository  extends JpaRepository<Historiaclinica, Integer> {

}
