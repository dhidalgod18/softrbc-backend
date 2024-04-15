package com.uan.optica.repository;

import com.uan.optica.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriaClinicaRepository  extends JpaRepository<Historiaclinica, Integer> {
    @Query("SELECT u FROM Paciente u WHERE u.idusuario = :idusuario")
    Paciente findPacienteId(@Param("idusuario") int idusuario);
    @Query("SELECT u FROM Anamnesis u WHERE u.idhistoriaclinica = :idhistoriaclinica")
    List<Anamnesis> findAnamnesis(@Param("idhistoriaclinica") int idhistoriaclinica);
    @Query("SELECT u FROM Antecedentes u WHERE u.idhistoriaclinica = :idhistoriaclinica")
    List<Antecedentes> findAntecedentes(@Param("idhistoriaclinica")  int idhistoriaclinica);
    @Query("SELECT u FROM RxUso u WHERE u.idhistoriaclinica = :idhistoriaclinica")
    List<RxUso> findRxUso(@Param("idhistoriaclinica") int idhistoriaclinica);
    @Query("SELECT u FROM VisionLejana u WHERE u.idhistoriaclinica = :idhistoriaclinica")
    List<VisionLejana> findVisionLejana(@Param("idhistoriaclinica") int idhistoriaclinica);
    @Query("SELECT u FROM VisionProxima u WHERE u.idhistoriaclinica = :idhistoriaclinica")
    List<VisionProxima> findVisionProxima(@Param("idhistoriaclinica") int idhistoriaclinica);
    @Query("SELECT u FROM Motilidad u WHERE u.idhistoriaclinica = :idhistoriaclinica")
    List<Motilidad> findMotilidad(@Param("idhistoriaclinica") int idhistoriaclinica);
    @Query("SELECT u FROM Oftalmoscopia u WHERE u.idhistoriaclinica = :idhistoriaclinica")
    List<Oftalmoscopia> findOftalmoscopia(@Param("idhistoriaclinica") int idhistoriaclinica);
    @Query("SELECT u FROM Queratometria u WHERE u.idhistoriaclinica = :idhistoriaclinica")
    List<Queratometria> findQueratometria(@Param("idhistoriaclinica") int idhistoriaclinica);
    @Query("SELECT u FROM Retinoscopia u WHERE u.idhistoriaclinica = :idhistoriaclinica")
    List<Retinoscopia> findRetinoscopia(@Param("idhistoriaclinica") int idhistoriaclinica);
    @Query("SELECT u FROM RxFinal u WHERE u.idhistoriaclinica = :idhistoriaclinica")
    List<RxFinal> findRxFinal(@Param("idhistoriaclinica") int idhistoriaclinica);

    @Query("SELECT u FROM Usuario u WHERE u.cedula = :cedula")
    Usuario findByUsuarioIdp(@Param("cedula") Long cedula);



}
