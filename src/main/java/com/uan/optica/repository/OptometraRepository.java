package com.uan.optica.repository;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptometraRepository extends JpaRepository<Optometra, Integer> {
    @Query("SELECT u FROM Optometra u WHERE u.idusuario = :idusuario")
    Optometra findByUsuarioId(@Param("idusuario") int idusuario);
    @Query("SELECT u FROM Usuario u WHERE u.rol = :rol")
    List<Usuario> findByRol(@Param("rol") String rol);


}
