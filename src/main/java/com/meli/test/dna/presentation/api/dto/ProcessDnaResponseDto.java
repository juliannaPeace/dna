package com.meli.test.dna.presentation.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ProcessDnaResponseDto implements Serializable {
    private Boolean isSimian;

    @JsonProperty("is_simian")
    public Boolean getSimian() {
        return isSimian;
    }

    public void setSimian(Boolean simian) {
        isSimian = simian;
    }
}
