package com.rocktech.controller;

import com.rocktech.dto.CandidateDetailsDto;
import com.rocktech.dto.CandidateDto;
import com.rocktech.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("candidates")
public class CandidateController {
    @Autowired
    private CandidateService service;

    @GetMapping
    public Flux<CandidateDto> all() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Mono<CandidateDetailsDto> get (@PathVariable String id) {
        return service.getOne(id);
    }

    @PostMapping
    public Mono<CandidateDto> create(@RequestBody Mono<CandidateDto> dtoMono) {
        return service.saveOne(dtoMono);
    }
}
