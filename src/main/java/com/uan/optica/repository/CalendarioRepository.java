package com.uan.optica.repository;

import com.uan.optica.entities.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Integer> {
}