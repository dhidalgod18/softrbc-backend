package com.uan.optica.repository;

import com.uan.optica.entities.Preguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, Integer> {

}
