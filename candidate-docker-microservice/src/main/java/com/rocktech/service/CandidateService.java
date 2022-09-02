package com.rocktech.service;

import com.rocktech.dto.CandidateDetailsDto;
import com.rocktech.dto.CandidateDto;
import com.rocktech.repository.CandidateRepository;
import com.rocktech.service.client.JobClient;
import com.rocktech.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository repository;

    @Autowired
    private JobClient client;

    public Flux<CandidateDto> getAll() {
        return repository.findAll().map(EntityDtoUtil::toDto);
    }

    public Mono<CandidateDetailsDto> getOne(String id) {
        return repository.findById(id).map(EntityDtoUtil::toDetailsDto)
                .flatMap(this::detailsDtoMono);
    }

    public Mono<CandidateDto> saveOne(Mono<CandidateDto> candidateMono) {
        return candidateMono.map(EntityDtoUtil::toEntity)
                .flatMap(repository::save)
                .map(EntityDtoUtil::toDto);
    }

    private Mono<CandidateDetailsDto> detailsDtoMono(CandidateDetailsDto dto) {
        return client.getRecommendedJobs(dto.getSkills())
                .doOnNext(dto::setRecommendedJobs)
                .thenReturn(dto);
    }


}
