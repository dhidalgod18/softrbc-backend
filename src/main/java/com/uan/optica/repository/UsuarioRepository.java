package com.uan.optica.repository;

import com.uan.optica.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
    /**
     * Un query personalizado
     */
    @Query("SELECT u FROM Usuario u WHERE u.cedula = :cedula")
    Usuario getUserByUserName(@Param("cedula") String cedula);
    @Query("SELECT u FROM Usuario u WHERE u.idusuario = :idusuario")
    Usuario obtener(@Param("idusuario") int idusuario);
    @Query("SELECT u FROM Usuario u WHERE u.rol = :rol")
    List<Usuario> findByRol(@Param("rol") String rol);

    @Query("SELECT u FROM Usuario u WHERE u.cedula = :cedula")
    Usuario findByUsuarioIdp(@Param("cedula") Long cedula);





}
