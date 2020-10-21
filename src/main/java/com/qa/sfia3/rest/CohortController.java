package com.qa.sfia3.rest;

import com.qa.sfia3.domain.Cohort;
import com.qa.sfia3.dto.CohortDTO;
import com.qa.sfia3.service.CohortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CohortController {

    private final CohortService cohortService;

    @Autowired
    public CohortController(CohortService cohortService) { this.cohortService = cohortService; }

    @GetMapping("/getAllCohorts")
    public ResponseEntity<List<CohortDTO>> getAllCohorts() {
        return ResponseEntity.ok(this.cohortService.getAllCohorts());
    }

    @PostMapping("/addCohort")
    public ResponseEntity<CohortDTO> addCohort(@RequestBody Cohort cohort) {
        return new ResponseEntity<CohortDTO>(this.cohortService.addCohort(cohort), HttpStatus.CREATED);
    }

    @GetMapping("/getCohortById/{id}")
    public ResponseEntity<CohortDTO> getCohortById(@PathVariable Long id) {
        return ResponseEntity.ok(this.cohortService.getCohortById(id));
    }

    @PutMapping("/editCohort/{id}")
    public ResponseEntity<CohortDTO> editCohort(@PathVariable Long id, @RequestBody Cohort cohort) {
        return ResponseEntity.ok(this.cohortService.editCohort(id, cohort));
    }

}
