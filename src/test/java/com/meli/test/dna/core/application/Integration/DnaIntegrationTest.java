package com.meli.test.dna.core.application.Integration;

import com.google.gson.Gson;
import com.meli.test.dna.core.application.util.DnaPostRequestBodyCreator;
import com.meli.test.dna.infrastructure.core.application.service.flyway.FlywayMigrationConfig;
import com.meli.test.dna.presentation.api.DnaApi;
import com.meli.test.dna.presentation.api.dto.ProcessDnaResponseDto;
import com.meli.test.dna.presentation.api.dto.ProcessDnaStatsResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(FlywayMigrationConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DnaIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("When sequence DNA contains 4 letter valid equals in sequence, When returns isSimian true and Success")
    void When_SequenceDnaIsSimianTrue_Then_ReturnsSuccess() {
        var processDnaRequestDto = DnaPostRequestBodyCreator.createDnaSimianPostRequestBody();

        System.out.println(new Gson().toJson(processDnaRequestDto));

        ResponseEntity<ProcessDnaResponseDto> processDnaResponse = testRestTemplate
                .postForEntity(DnaApi.URI_PROCESS_DNA, processDnaRequestDto, ProcessDnaResponseDto.class);

        Assertions.assertThat(processDnaResponse).isNotNull();
        Assertions.assertThat(processDnaResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(processDnaResponse.getBody()).isNotNull();
        Assertions.assertThat(processDnaResponse.getBody().getIsSimian()).isTrue();

    }

    @Test
    @DisplayName("When sequence DNA does not contains 4 letter valid equals in sequence, When returns isSimian false and Success")
    void When_SequenceDnaIsSimianFalse_Then_ReturnsSuccess() {
        var processDnaRequestDto = DnaPostRequestBodyCreator.createDnaHumanPostRequestBody();

        ResponseEntity<ProcessDnaResponseDto> processDnaResponse = testRestTemplate
                .postForEntity(DnaApi.URI_PROCESS_DNA, processDnaRequestDto, ProcessDnaResponseDto.class);

        Assertions.assertThat(processDnaResponse).isNotNull();
        Assertions.assertThat(processDnaResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(processDnaResponse.getBody()).isNotNull();
        Assertions.assertThat(processDnaResponse.getBody().getIsSimian()).isFalse();

    }

    @Test
    @DisplayName("When sequence DNA is not letters ATGC, Then returns Bad Request ")
    void When_SequenceDnaIsNotDna_Then_ReturnBadRequest() {
        var processDnaRequestDto = DnaPostRequestBodyCreator.createSequenceDnaInvalidPostRequestBody();

        ResponseEntity<ProcessDnaResponseDto> processDnaResponse = testRestTemplate
                .postForEntity(DnaApi.URI_PROCESS_DNA, processDnaRequestDto, ProcessDnaResponseDto.class);

        Assertions.assertThat(processDnaResponse).isNotNull();
        Assertions.assertThat(processDnaResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(processDnaResponse.getBody()).isNotNull();
        Assertions.assertThat(processDnaResponse.getBody().getIsSimian()).isNull();

    }

    @Test
    @DisplayName("When sequence DNA is not matrix quadratic, Then returns Bad Request ")
    void When_SequenceDnaIsNotQuadratic_Then_ReturnBadRequest() {
        var processDnaRequestDto = DnaPostRequestBodyCreator.createSequenceDnaNotQuadraticPostRequestBody();

        ResponseEntity<ProcessDnaResponseDto> processDnaResponse = testRestTemplate
                .postForEntity(DnaApi.URI_PROCESS_DNA, processDnaRequestDto, ProcessDnaResponseDto.class);

        Assertions.assertThat(processDnaResponse).isNotNull();
        Assertions.assertThat(processDnaResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(processDnaResponse.getBody()).isNotNull();
        Assertions.assertThat(processDnaResponse.getBody().getIsSimian()).isNull();

    }

    @Test
    @DisplayName("When sequence DNA is valid and exists, Then returns Conflict of data ")
    void When_SequenceDnaIsValid_AndExists_Then_ReturnConflict() {
        var processDnaRequestDto = DnaPostRequestBodyCreator.getSequenceDnaValidExistingPostRequestBody();

        ResponseEntity<ProcessDnaResponseDto> processDnaResponse = testRestTemplate
                .postForEntity(DnaApi.URI_PROCESS_DNA, processDnaRequestDto, ProcessDnaResponseDto.class);

        Assertions.assertThat(processDnaResponse).isNotNull();
        Assertions.assertThat(processDnaResponse.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        Assertions.assertThat(processDnaResponse.getBody()).isNotNull();
        Assertions.assertThat(processDnaResponse.getBody().getIsSimian()).isNull();

    }

    @Test
    @DisplayName("When request stats humans and simians, Then returns count human, count simians , ratio and Success")
    void When_RequestStatsHumanAndSimian_Then_ReturnCountHuman_CountSimian_AndRatio() {
        ResponseEntity<ProcessDnaStatsResponseDto> processDnaStatsResponse = testRestTemplate
                .getForEntity(DnaApi.URI_PROCESS_DNA_STATS, ProcessDnaStatsResponseDto.class);

        Assertions.assertThat(processDnaStatsResponse).isNotNull();
        Assertions.assertThat(processDnaStatsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(processDnaStatsResponse.getBody()).isNotNull();
        Assertions.assertThat(processDnaStatsResponse.getBody().getCountSimianDna()).isEqualTo(1);
        Assertions.assertThat(processDnaStatsResponse.getBody().getCountHumanDna()).isEqualTo(2);
        Assertions.assertThat(processDnaStatsResponse.getBody().getRatio()).isEqualTo(0.5);

    }
}
