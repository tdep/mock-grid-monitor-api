package com.trevor.portfolio.mockapi.repository;

import com.trevor.portfolio.mockapi.model.PowerStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerStationRepository extends JpaRepository<PowerStation, Long> {
    
}