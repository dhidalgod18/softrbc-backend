package com.uan.optica.repository;

import com.uan.optica.entities.Queratometria;
import com.uan.optica.entities.RxUso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueratometriaRepository extends JpaRepository<Queratometria, Integer> {
}
