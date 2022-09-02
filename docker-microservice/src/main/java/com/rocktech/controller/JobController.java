package com.rocktech.controller;

import com.rocktech.dto.JobDto;
import com.rocktech.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("all")
    public Flux<JobDto> all() {
        return service.allJobs();
    }

    @GetMapping("search")
    public Flux<JobDto> search(@RequestParam Set<String> skills) {
        return service.jobsBySkill(skills);
    }

    @PostMapping
    public Mono<JobDto> insert(@RequestBody Mono<JobDto> dtoMono) {
        return service.save(dtoMono);
    }
}
