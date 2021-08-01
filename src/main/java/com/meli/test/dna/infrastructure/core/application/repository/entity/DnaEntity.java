package com.meli.test.dna.infrastructure.core.application.repository.entity;

import com.meli.test.dna.core.domain.model.Dna;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    public DnaEntity() {
    }

    public DnaEntity(UUID id, String sequenceDna) {
        this.id = id;
        this.sequenceDna = sequenceDna;
    }

    public Dna dnaEntityToDna() {
        return new Dna(this.id, this.sequenceDna);
    }

    public DnaEntity dnaToDnaEntity(Dna dna) {
        return new DnaEntity(UUID.randomUUID(), dna.getSequenceDna());
    }

}
