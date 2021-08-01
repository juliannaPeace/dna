package com.meli.test.dna.core.application.service;

import com.meli.test.dna.core.application.repository.DnaRepository;
import com.meli.test.dna.core.domain.model.Dna;
import com.meli.test.dna.infrastructure.core.application.repository.entity.DnaEntity;
import org.springframework.stereotype.Service;

@Service
public class DnaService {

    private final DnaRepository dnaRepository;

    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public Boolean resultProcessSequenceDna(Dna dna) {

        dnaRepository.save(new DnaEntity().dnaToDnaEntity(dna));
        return true;
    }
}
