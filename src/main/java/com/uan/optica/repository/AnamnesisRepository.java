package com.uan.optica.repository;

import com.uan.optica.entities.Anamnesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AnamnesisRepository extends JpaRepository<Anamnesis, Integer> {
}
