package com.rocktech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {
    private String id;
    private String name;
    private Set<String> skills;
    private String hostName;
}
