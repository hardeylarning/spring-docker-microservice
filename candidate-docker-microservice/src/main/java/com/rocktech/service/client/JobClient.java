package com.rocktech.service.client;

import com.rocktech.dto.JobDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class JobClient {
    private final WebClient webClient;

    public JobClient(@Value("${job.service.url}") String baseUrl) {
        webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<List<JobDto>> getRecommendedJobs(Set<String> skills) {
        return webClient.get()
                .uri(u -> u.path("search").queryParam("skills", skills).build())
                .retrieve()
                .bodyToFlux(JobDto.class)
                .collectList()
                .onErrorReturn(Collections.emptyList());
    }
}
