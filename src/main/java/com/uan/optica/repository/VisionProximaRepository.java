package com.uan.optica.repository;

import com.uan.optica.entities.VisionLejana;
import com.uan.optica.entities.VisionProxima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisionProximaRepository extends JpaRepository<VisionProxima, Integer> {
}