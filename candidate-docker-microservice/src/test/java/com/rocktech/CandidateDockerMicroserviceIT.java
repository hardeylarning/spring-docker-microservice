package com.rocktech;

import com.rocktech.dto.CandidateDto;
import com.rocktech.generic.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
class CandidateDockerMicroserviceIT extends BaseTest {

    @Autowired
    WebTestClient client;

    @Test
    void allCandidates() {
        client.get()
                .uri("/candidates")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    @DisplayName("you got id?")
    void getCandidateById() {
        client.get()
                .uri("/candidates/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.skills.size()").isEqualTo(4);
    }

    @Test
    void addCandidate() {
        var dto = new CandidateDto(null, "Victor Osimeh", Set.of("Scorer", "Header"), null);
        client.post()
                .uri("/candidates")
                .bodyValue(dto)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Victor Osimeh");
    }

    @Test
    @DisplayName("you got id?")
    void jobServiceReturnsError() {
        client.get()
                .uri("/candidates/7")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(7)
                .jsonPath("$.skills.size()").isEqualTo(1)
                .jsonPath("$.recommendedJobs.size()").isEqualTo(0);
    }

}
