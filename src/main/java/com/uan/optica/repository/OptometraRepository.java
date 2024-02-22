package com.uan.optica.repository;

import com.uan.optica.entities.Optometra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptometraRepository extends JpaRepository<Optometra, Integer> {
}
