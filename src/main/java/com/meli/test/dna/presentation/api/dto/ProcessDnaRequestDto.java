package com.meli.test.dna.presentation.api.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProcessDnaRequestDto {
    @NotNull(message = "Required field")
    private List<String> dna;
}
