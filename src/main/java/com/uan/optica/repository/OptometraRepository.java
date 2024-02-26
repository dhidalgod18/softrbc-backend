package com.uan.optica.repository;

import com.uan.optica.entities.Optometra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OptometraRepository extends JpaRepository<Optometra, Integer> {
    @Query("SELECT u FROM Optometra u WHERE u.idusuario = :idusuario")
    Optometra findByUsuarioId(@Param("idusuario") int idusuario);


}
