package com.meli.test.dna;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.meli.test.dna.core.application.repository.DnaRepository;
import com.meli.test.dna.core.domain.model.Dna;
import com.meli.test.dna.infrastructure.core.application.repository.entity.DnaEntity;
import com.meli.test.dna.infrastructure.core.application.service.flyway.FlywayMigrationConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Import(FlywayMigrationConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DnaRepositoryTests {
    @Autowired
    private DnaRepository dnaRepository;

    @Autowired
    private FlywayMigrationStrategy strategy;

    @Test
    @DisplayName("Save persists dna when success")
    void save_PersistDna_WhenSuccess() {
        Dna dnaPersona = createDna();
        DnaEntity dnaSaved = this.dnaRepository.save(new DnaEntity().dnaToDnaEntity(dnaPersona));
        assertThat(dnaSaved).isNotNull();
        assertThat(dnaSaved.dnaEntityToDna().getId()).isNotNull();
        assertThat(dnaSaved.dnaEntityToDna().getSequenceDna()).isNotNull();
        assertThat(dnaPersona.getIsSimian()).isEqualTo(dnaSaved.dnaEntityToDna().getIsSimian());
    }

    @Test
    @DisplayName("Save throws DataIntegrityViolationException when DNA sequence exists")
    void save_Throws_DataIntegrityViolationException_WhenDnaExists() {
        Dna dnaPersona = existingDna();

        assertThatThrownBy(() -> this.dnaRepository.save(new DnaEntity().dnaToDnaEntity(dnaPersona)))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("Find by sequence DNA return DNA sequence when exists")
    void findBySequenceDna_ReturnDna_WhenDnaExists() {
        Dna dnaPersona = existingDna();

        JsonArray json = new Gson().toJsonTree(dnaPersona.getSequenceDna()).getAsJsonArray();
        var dnaFound = this.dnaRepository.findBySequenceDna(json.toString());

        assertThat(dnaFound).isNotNull();
        assertThat(dnaFound.dnaEntityToDna().getSequenceDna()).contains(json.toString());
    }

    @Test
    @DisplayName("Count Simians return amount simian DNA sequence when exists")
    void countSimians_ReturnAmountSimiansDna_WhenDnaSimiansExists() {
        var simianFound = this.dnaRepository.countSimions();

        assertThat(simianFound).isNotNull();
        assertThat(simianFound).isGreaterThan(0);
    }

    @Test
    @DisplayName("Count Humans return amount human DNA sequence when exists")
    void countHumans_ReturnAmountHumanDna_WhenDnaHumansExists() {
        var humanFound = this.dnaRepository.countHumans();

        assertThat(humanFound).isNotNull();
        assertThat(humanFound).isGreaterThan(0);
    }

    private Dna createDna() {
        return Dna.builder()
                .id(UUID.randomUUID())
                .sequenceDna(Arrays.asList("CTGAGA", "CCCCCC", "TTGACA", "AAAAAA", "TTGAGC", "CTCAGC"))
                .isSimian(true)
                .build();
    }

    private Dna existingDna() {
        return Dna.builder()
                .id(UUID.randomUUID())
                .sequenceDna(Arrays.asList("CTGAGA", "CTATGC", "TATTGT", "AGATGG", "CCCCTA", "TCACTT"))
                .isSimian(true)
                .build();
    }
}
