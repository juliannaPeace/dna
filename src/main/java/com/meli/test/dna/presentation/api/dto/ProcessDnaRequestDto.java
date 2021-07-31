package com.meli.test.dna.presentation.api.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class ProcessDnaRequestDto implements Serializable {
    @NotNull(message = "não pode")
    private List<String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }
}
