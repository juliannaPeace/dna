package com.meli.test.dna.infrastructure.core.application.repository.entity;

import com.google.gson.Gson;
import com.meli.test.dna.core.domain.model.Dna;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dna")
@AllArgsConstructor
@NoArgsConstructor
public class DnaEntity {
    @Id
    private UUID id;

//    @Type(type = "jsonb")
    @Column(columnDefinition = "TEXT")
    private String sequenceDna;
    private Boolean isSimian;

    public Dna dnaEntityToDna() {
        return new Dna(this.id, List.of(this.sequenceDna), this.isSimian);
    }

    public DnaEntity dnaToDnaEntity(Dna dna) {
        return new DnaEntity(UUID.randomUUID(), new Gson().toJson(dna.getSequenceDna()), dna.getIsSimian());
    }

}
