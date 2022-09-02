package com.rocktech.service;

import com.rocktech.dto.JobDto;
import com.rocktech.repository.JobRepository;
import com.rocktech.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    public Flux<JobDto> allJobs(){
        return repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Flux<JobDto> jobsBySkill(Set<String> skills){
        return repository.findBySkillsIn(skills)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<JobDto> save(Mono<JobDto> mono) {
        return mono.map(EntityDtoUtil::toEntity)
                .flatMap(repository::save)
                .map(EntityDtoUtil::toDto);
    }


}
