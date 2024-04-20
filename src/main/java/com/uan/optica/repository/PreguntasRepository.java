package com.uan.optica.repository;

import com.uan.optica.entities.Cita;
import com.uan.optica.entities.Preguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, Integer> {
    @Query("SELECT u FROM Preguntas u WHERE u.idpregunta = :idpregunta")
    Preguntas lista(@Param("idpregunta") int idpregunta);

}
