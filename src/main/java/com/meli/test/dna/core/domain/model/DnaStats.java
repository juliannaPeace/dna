package com.meli.test.dna.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DnaStats {
    private int countSimianDna;
    private int countHumanDna;
    private Double ratio;
}
