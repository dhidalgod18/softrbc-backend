package com.uan.optica.repository;

import com.uan.optica.entities.Cita;
import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    @Query("SELECT c.hora FROM Cita c WHERE c.fecha LIKE %:fecha%")
    List<String> obtenerHoras(@Param("fecha") String fecha);

    @Query("SELECT u FROM Cita u WHERE u.idpaciente = :idpaciente")
    Cita getCita(@Param("idpaciente") int idpaciente);

    @Query("SELECT c FROM Cita c WHERE c.codigo = :codigo")
    Cita findByCodigo(@Param("codigo") String codigo);
    @Query("SELECT c FROM Cita c WHERE c.fecha = :fecha")
    List<Cita> obtenerCitasPorFecha(@Param("fecha") String fecha);
    void deleteById(int id);

}
