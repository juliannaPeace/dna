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

        var isSimian = false;

        for (int i = 0; i < dna.getSequenceDna().size(); i++) {
            for (int j = 0; j < dna.getSequenceDna().size(); j++) {
                isSimian = dna.verificaHorizontal(i, j);
            }
        }

        dna.setSimian(isSimian);
        dnaRepository.save(new DnaEntity().dnaToDnaEntity(dna));
        return dna.getSimian();
    }
}
