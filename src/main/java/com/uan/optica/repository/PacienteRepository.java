package com.uan.optica.repository;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
