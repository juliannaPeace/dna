package com.meli.test.dna.core.application.repository;

import com.meli.test.dna.infrastructure.core.application.repository.entity.DnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DnaRepository extends JpaRepository<DnaEntity, UUID> {

    @Query(" Select count(1) from DnaEntity " +
            "where is_simian = true " +
            "group by isSimian ")
    public Integer countSimions();

    @Query(" Select count(1) from DnaEntity " +
            "where is_simian = false " +
            "group by isSimian ")
    public Integer countHumans();

    public DnaEntity findBySequenceDna(String sequenceDna);
}
