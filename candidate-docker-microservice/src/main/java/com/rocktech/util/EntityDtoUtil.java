package com.rocktech.util;

import com.rocktech.dto.CandidateDetailsDto;
import com.rocktech.dto.CandidateDto;
import com.rocktech.model.Candidate;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static CandidateDto toDto (Candidate candidate) {
        CandidateDto dto = new CandidateDto();
        BeanUtils.copyProperties(candidate, dto);
        dto.setHostName(AppUtil.getHOSTNAME());
        return dto;
    }

    public static CandidateDetailsDto toDetailsDto (Candidate candidate) {
        CandidateDetailsDto dto = new CandidateDetailsDto();
        BeanUtils.copyProperties(candidate, dto);
        dto.setHostName(AppUtil.getHOSTNAME());
        return dto;
    }

    public static Candidate toEntity(CandidateDto dto) {
        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(dto, candidate);
        return candidate;

    }
}
