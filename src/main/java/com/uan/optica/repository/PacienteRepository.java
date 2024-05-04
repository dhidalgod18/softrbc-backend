package com.uan.optica.repository;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Paciente;
import com.uan.optica.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.rol = :rol")
    List<Usuario> findByRol(@Param("rol") String rol);
    @Query("SELECT u FROM Paciente u WHERE u.idusuario = :idusuario")
    Paciente findById(@Param("idusuario") int idusuario);
    @Query("SELECT u FROM Paciente u WHERE u.idpaciente = :idpaciente")
    Paciente findPacienteId(@Param("idpaciente") int idpaciente);

    @Query("SELECT u FROM Paciente u WHERE u.idusuario = :idusuario")
    Paciente findByUsuarioId(@Param("idusuario") int idusuario);

    @Query("SELECT u FROM Usuario u WHERE u.idusuario = :idusuario")
    Usuario findByUsuarioIdp(@Param("idusuario") int idusuario);

}
