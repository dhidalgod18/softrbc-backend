package com.uan.optica.repository;

import com.uan.optica.entities.Motilidad;
import com.uan.optica.entities.RxUso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotilidadRepository extends JpaRepository<Motilidad, Integer> {
}
