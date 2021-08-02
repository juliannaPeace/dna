package com.meli.test.dna.core.application.service;

import com.meli.test.dna.core.application.repository.DnaRepository;
import com.meli.test.dna.core.domain.model.Dna;
import com.meli.test.dna.infrastructure.core.application.repository.entity.DnaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DnaService {

    private final DnaRepository dnaRepository;

    public Boolean resultProcessSequenceDna(Dna dna) {
        dna.verifyIsSimian();
        dnaRepository.save(new DnaEntity().dnaToDnaEntity(dna));
        return dna.getIsSimian();
    }
}
