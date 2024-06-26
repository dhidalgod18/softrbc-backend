package com.uan.optica.repository;

import com.uan.optica.entities.Calendario;
import com.uan.optica.entities.Cita;
import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Integer> {
    @Query("SELECT c.duracioncita FROM Calendario c WHERE c.diasatencion LIKE %:dia%")
    Integer obtenerDuracionCitaPorDia(@Param("dia") String dia);

    @Query("SELECT u FROM Optometra u WHERE u.idoptometra = :idoptometra")
    Optometra findByUsuarioId(@Param("idoptometra") int idoptometra);


}