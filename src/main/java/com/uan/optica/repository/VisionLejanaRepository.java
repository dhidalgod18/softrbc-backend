package com.uan.optica.repository;

import com.uan.optica.entities.VisionLejana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisionLejanaRepository extends JpaRepository<VisionLejana, Integer> {
}
