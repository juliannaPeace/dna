package com.meli.test.dna.core.application.util;

import com.meli.test.dna.presentation.api.dto.ProcessDnaRequestDto;

public class DnaPostRequestBodyCreator {

    public static ProcessDnaRequestDto createDnaSimianPostRequestBody(){
        return ProcessDnaRequestDto.builder()
                .dna(DnaPersonaCreator.createDnaSimianIntegration().getSequenceDna())
                .build();
    }

    public static ProcessDnaRequestDto createDnaHumanPostRequestBody(){
        return ProcessDnaRequestDto.builder()
                .dna(DnaPersonaCreator.createDnaHumanIntegration().getSequenceDna())
                .build();
    }

    public static ProcessDnaRequestDto createSequenceDnaInvalidPostRequestBody(){
        return ProcessDnaRequestDto.builder()
                .dna(DnaPersonaCreator.createSequenceDnaInvalid().getSequenceDna())
                .build();
    }

    public static ProcessDnaRequestDto createSequenceDnaNotQuadraticPostRequestBody(){
        return ProcessDnaRequestDto.builder()
                .dna(DnaPersonaCreator.createSequenceDnaNotQuadratic().getSequenceDna())
                .build();
    }

    public static ProcessDnaRequestDto getSequenceDnaValidExistingPostRequestBody(){
        return ProcessDnaRequestDto.builder()
                .dna(DnaPersonaCreator.existingDna().getSequenceDna())
                .build();
    }
}
