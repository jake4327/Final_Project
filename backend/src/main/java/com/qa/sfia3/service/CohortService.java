package com.qa.sfia3.service;

import com.qa.sfia3.domain.Cohort;
import com.qa.sfia3.dto.CohortDTO;
import com.qa.sfia3.exceptions.CohortNotFoundException;
import com.qa.sfia3.repo.CohortRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CohortService {

    private final CohortRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public CohortService(CohortRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public CohortDTO mapToDTO(Cohort cohort) { return this.mapper.map(cohort, CohortDTO.class); }

    public CohortDTO getCohortById(Long id) {
        return this.mapToDTO(this.repo.findById(id).orElseThrow(CohortNotFoundException::new));
    }

    public List<CohortDTO> getAllCohorts() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CohortDTO addCohort(Cohort cohort) { return this.mapToDTO(this.repo.save(cohort)); }

    public CohortDTO editCohort(Long id, Cohort cohort) {
        Cohort update = this.repo.findById(id).orElseThrow(CohortNotFoundException::new);
        update.setName(cohort.getName());
        return this.mapToDTO(this.repo.save(update));
    }

}
