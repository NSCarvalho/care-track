package com.caretrack.business.repos.interfaces;

import com.caretrack.business.repos.entities.SymptomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepository extends JpaRepository<SymptomEntity, Long> {
}
