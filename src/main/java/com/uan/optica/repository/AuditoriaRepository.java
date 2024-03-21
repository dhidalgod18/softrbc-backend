package com.uan.optica.repository;

import com.uan.optica.entities.Auditoria;
import com.uan.optica.entities.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
}
