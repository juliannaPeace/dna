package com.meli.test.dna.presentation.rest;

import com.meli.test.dna.presentation.api.SimianApi;
import com.meli.test.dna.presentation.api.dto.ProcessDnaRequestDto;
import com.meli.test.dna.presentation.api.dto.ProcessDnaResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class SimianEndpoint {

    @PostMapping(SimianApi.URI_PROCESS_DNA)
    public ResponseEntity<ProcessDnaResponseDto> processDna(@Valid @RequestBody ProcessDnaRequestDto processDnaRequestDto) {

        processDnaRequestDto.getDna().forEach(dna -> {
            System.out.println(dna);
        });

        var processDnaResponseDto = new ProcessDnaResponseDto();
        processDnaResponseDto.setSimian(true);

        return ResponseEntity.ok(processDnaResponseDto);
    }
}
