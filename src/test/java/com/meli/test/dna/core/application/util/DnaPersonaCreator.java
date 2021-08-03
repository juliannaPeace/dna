package com.meli.test.dna.core.application.util;

import com.meli.test.dna.core.domain.model.Dna;

import java.util.Arrays;
import java.util.UUID;

public class DnaPersonaCreator {

    public static Dna createDna() {
        return Dna.builder()
                .id(UUID.randomUUID())
                .sequenceDna(Arrays.asList("CTGAGA", "CCCCCC", "TTGACA", "AAAAAA", "TTGAGC", "CTCAGC"))
                .isSimian(true)
                .build();
    }

    public static Dna existingDna() {
        return Dna.builder()
                .id(UUID.randomUUID())
                .sequenceDna(Arrays.asList("CTGAGA", "CTATGC", "TATTGT", "AGATGG", "CCCCTA", "TCACTT"))
                .isSimian(true)
                .build();
    }

    public static Dna createDnaSimianIntegration() {
        return Dna.builder()
                .id(UUID.randomUUID())
                .sequenceDna(Arrays.asList("TGCTAAGTAG", "ACTTCAGTCA", "CATGTTCTAG", "GAACTTTTGT", "CGAAGAGGGC",
                        "CAGAGAGGAT", "ATCCACTATA", "ACGGCCATCC", "GTCCCTCTCT", "TTTACGTCAA"))
                .isSimian(true)
                .build();
    }

    public static Dna createDnaHumanIntegration() {
        return Dna.builder()
                .id(UUID.randomUUID())
                .sequenceDna(Arrays.asList("GACA", "CACC", "CATG", "ACCA"))
                .isSimian(false)
                .build();
    }

    public static Dna createSequenceDnaInvalid() {
        return Dna.builder()
                .id(UUID.randomUUID())
                .sequenceDna(Arrays.asList("XXXX", "CACC", "CATG", "ACCA"))
                .build();
    }

    public static Dna createSequenceDnaNotQuadratic() {
        return Dna.builder()
                .id(UUID.randomUUID())
                .sequenceDna(Arrays.asList("CATGGG", "CACC", "CATG", "ACCA"))
                .build();
    }
}
