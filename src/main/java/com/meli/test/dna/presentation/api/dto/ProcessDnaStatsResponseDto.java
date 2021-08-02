package com.meli.test.dna.presentation.api.dto;

import com.meli.test.dna.core.domain.model.DnaStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDnaStatsResponseDto{
    private int countSimianDna;
    private int countHumanDna;
    private Double ratio;


    public ProcessDnaStatsResponseDto dnaStatsToProcessDnaStatsResponseDto(DnaStats dnaStats) {
        return ProcessDnaStatsResponseDto.builder().
                countSimianDna(dnaStats.getCountSimianDna()).
                countHumanDna(dnaStats.getCountHumanDna()).
                ratio(dnaStats.getRatio()).build();
    }
}
