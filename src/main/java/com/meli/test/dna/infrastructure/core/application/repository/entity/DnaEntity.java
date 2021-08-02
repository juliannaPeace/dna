package com.meli.test.dna.infrastructure.core.application.repository.entity;

import com.google.gson.Gson;
import com.meli.test.dna.core.domain.model.Dna;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dna")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class DnaEntity {
    @Id
    private UUID id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "json")
    private String sequenceDna;
    private Boolean isSimian;

    public DnaEntity() {
    }

    public DnaEntity(UUID id, String sequenceDna, Boolean isSimian) {
        this.id = id;
        this.sequenceDna = sequenceDna;
        this.isSimian = isSimian;
    }

    public Dna dnaEntityToDna() {
        return new Dna(this.id, List.of(this.sequenceDna), this.isSimian);
    }

    public DnaEntity dnaToDnaEntity(Dna dna) {
        return new DnaEntity(UUID.randomUUID(), new Gson().toJson(dna.getSequenceDna()), dna.getSimian());
    }

}
