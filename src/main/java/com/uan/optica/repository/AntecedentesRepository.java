package com.uan.optica.repository;

import com.uan.optica.entities.Antecedentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedentesRepository extends JpaRepository<Antecedentes, Integer> {
}
