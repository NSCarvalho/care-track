package com.caretrack.business.repos.interfaces;

import com.caretrack.business.repos.entities.PathologyEntity;
import com.caretrack.business.repos.entities.SymptomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathologyRepository extends JpaRepository<PathologyEntity, Long> {
}
