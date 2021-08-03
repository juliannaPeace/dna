package com.meli.test.dna.core.application.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.meli.test.dna.core.application.repository.DnaRepository;
import com.meli.test.dna.core.domain.exception.DnaSequenceException;
import com.meli.test.dna.core.domain.exception.QuadraticMatrixException;
import com.meli.test.dna.core.domain.exception.UniqueConstraintException;
import com.meli.test.dna.core.domain.model.Dna;
import com.meli.test.dna.core.domain.model.DnaStats;
import com.meli.test.dna.infrastructure.core.application.repository.entity.DnaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DnaService {

    private final DnaRepository dnaRepository;

    public Boolean resultProcessSequenceDna(Dna dna) {
        validate(dna);
        dna.verifyIsSimian();
        dnaRepository.save(new DnaEntity().dnaToDnaEntity(dna));
        return dna.getIsSimian();
    }

    public DnaStats getStatsProcessDna() {
        var countSimian = dnaRepository.countSimions();
        var countHuman = dnaRepository.countHumans();

        return DnaStats.builder()
                .countSimianDna(countSimian)
                .countHumanDna(countHuman)
                .ratio(countSimian.doubleValue() / countHuman.doubleValue()).build();
    }

    private void validate(Dna dna) {
        var regexDna = "[ATGC]+";

        JsonArray json = new Gson().toJsonTree(dna.getSequenceDna()).getAsJsonArray();

        if (dnaRepository.findBySequenceDna(json.toString()) != null) {
            throw new UniqueConstraintException("Sequence DNA already exists");
        }

        for (int i = 0; i < dna.getSequenceDna().size(); i++) {
            if (dna.getSequenceDna().size() != dna.getSequenceDna().get(i).length()) {
                throw new QuadraticMatrixException("Informed matrix is not quadratic");
            }

            if (!dna.getSequenceDna().get(i).matches(regexDna)) {
                throw new DnaSequenceException("The value informed is not a DNA");
            }
        }
    }
}
