package com.rocktech;

import com.rocktech.dto.JobDto;
import com.rocktech.generic.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
class JobServiceIT extends BaseTest {


    // to run through command line, use 'mvn clean verify

    @Autowired
    private WebTestClient client;

    @Test
    void allJobsTest() {
        client.get()
                .uri("/job/all")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void searchBySkillsTest() {
        client.get()
                .uri("/job/search?skills=Java")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    void postJobTest() {
        var dto = JobDto.create(null, "k8s engr",
                "google", Set.of("k8s", "Jira", "Python"), 230000, true, null);
        client.post()
                .uri("/job")
                .bodyValue(dto)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.company").isEqualTo("google");
    }



}
