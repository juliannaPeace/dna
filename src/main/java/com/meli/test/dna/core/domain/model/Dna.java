package com.meli.test.dna.core.domain.model;

import java.util.List;
import java.util.UUID;

public class Dna {

    private UUID id;
    private String sequenceDna;

    public Dna() {
    }

    public Dna(String sequenceDna) {
        this.sequenceDna = sequenceDna;
    }

    public Dna(UUID id, String sequenceDna) {
        this.id = id;
        this.sequenceDna = sequenceDna;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSequenceDna() {
        return sequenceDna;
    }

    public void setSequenceDna(String sequenceDna) {
        this.sequenceDna = sequenceDna;
    }
}
