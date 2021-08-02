package com.meli.test.dna.presentation.rest;

import com.meli.test.dna.core.application.service.DnaService;
import com.meli.test.dna.core.domain.model.Dna;
import com.meli.test.dna.presentation.api.DnaApi;
import com.meli.test.dna.presentation.api.dto.ProcessDnaRequestDto;
import com.meli.test.dna.presentation.api.dto.ProcessDnaResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class DnaEndpoint {
    private final DnaService dnaService;

    @ApiOperation(value = "Retorna se um determinando DNA é de um simio ou de um humano")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna se um DNA pertence a um Simio ou a um Humano"),
            @ApiResponse(code = 404, message = "Foi enviado um payload inválido"),
            @ApiResponse(code = 500, message = "Ocorreu erro interno no servidor"),
    })
    @PostMapping(DnaApi.URI_PROCESS_DNA)
    public ResponseEntity<ProcessDnaResponseDto> processDna(@Valid @RequestBody ProcessDnaRequestDto processDnaRequestDto) {

        var result = dnaService.resultProcessSequenceDna(new Dna(processDnaRequestDto.getDna()));
        var processDnaResponseDto = new ProcessDnaResponseDto(result);

        return ResponseEntity.ok(processDnaResponseDto);
    }
}
