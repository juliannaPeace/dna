package com.meli.test.dna.presentation.rest;

import com.google.gson.Gson;
import com.meli.test.dna.core.application.service.DnaService;
import com.meli.test.dna.core.domain.model.Dna;
import com.meli.test.dna.presentation.api.DnaApi;
import com.meli.test.dna.presentation.api.dto.ProcessDnaRequestDto;
import com.meli.test.dna.presentation.api.dto.ProcessDnaResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class DnaEndpoint {

    private final DnaService dnaService;

    public DnaEndpoint(DnaService dnaService) {
        this.dnaService = dnaService;
    }

    @PostMapping(DnaApi.URI_PROCESS_DNA)
    public ResponseEntity<ProcessDnaResponseDto> processDna(@Valid @RequestBody ProcessDnaRequestDto processDnaRequestDto) {

        var dnaJson = new Gson().toJson(processDnaRequestDto.getDna());
        var result = dnaService.resultProcessSequenceDna(new Dna(dnaJson));
        var processDnaResponseDto = new ProcessDnaResponseDto(result);

        return ResponseEntity.ok(processDnaResponseDto);
    }
}
