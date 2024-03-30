package com.uan.optica.repository;

import com.uan.optica.entities.Antecedentes;
import com.uan.optica.entities.RxUso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RxUsoRepository extends JpaRepository<RxUso, Integer> {
}
