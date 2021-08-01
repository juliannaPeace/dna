package com.meli.test.dna.core.application.repository;

import com.meli.test.dna.infrastructure.core.application.repository.entity.DnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DnaRepository extends JpaRepository<DnaEntity, UUID> {
}
