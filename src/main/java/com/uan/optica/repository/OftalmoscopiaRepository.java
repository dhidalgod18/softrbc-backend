package com.uan.optica.repository;

import com.uan.optica.entities.Oftalmoscopia;
import com.uan.optica.entities.RxUso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OftalmoscopiaRepository extends JpaRepository<Oftalmoscopia, Integer> {
}
