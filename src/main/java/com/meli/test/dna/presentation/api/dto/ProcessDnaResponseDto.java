package com.meli.test.dna.presentation.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProcessDnaResponseDto {
    @JsonProperty("is_simian")
    private Boolean isSimian;

}
