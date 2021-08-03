package com.meli.test.dna.presentation.api.dto;

import com.meli.test.dna.core.domain.model.DnaStats;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDnaStatsResponseDto {
    private int countSimianDna;
    private int countHumanDna;
    @Getter(AccessLevel.NONE)
    private Double ratio;


    public ProcessDnaStatsResponseDto dnaStatsToProcessDnaStatsResponseDto(DnaStats dnaStats) {
        return ProcessDnaStatsResponseDto.builder().
                countSimianDna(dnaStats.getCountSimianDna()).
                countHumanDna(dnaStats.getCountHumanDna()).
                ratio(dnaStats.getRatio()).build();
    }

    public Double getRatio() {
        return Double.parseDouble(String.format("%.1f", ratio).replace(",", "."));
    }
}
