package com.qa.sfia3.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.qa.sfia3.domain.Cohort;
import com.qa.sfia3.dto.CohortDTO;
import com.qa.sfia3.repo.CohortRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest
@ActiveProfiles(profiles = "test")
public class CohortServiceUnitTest {

    @Autowired
    private CohortService service;

    @MockBean
    private CohortDTO DTO;

    @MockBean
    private CohortRepository repo;

    @Test
    void testAddCohort(){
        //Given
        Long id = 1L;
        Cohort newCohort = new Cohort ("Areeb");
        Cohort savedCohort = new Cohort("Areeb");
        savedCohort.setCohortId(id);
        //When
        Mockito.when(this.repo.save(newCohort)).thenReturn(savedCohort);
        //then
        assertThat(this.service.addCohort(newCohort)).isEqualToComparingOnlyGivenFields(savedCohort, "name");

        Mockito.verify(this.repo, Mockito.times(1)).save(newCohort);
    }
    @Test
    void testEditCohort() {
        // GIVEN

        Long id = 1L;

        // will be passed in
        Cohort newCohort = new Cohort("Areeb");
        // will be found by findById()
        Cohort oldCohort = new Cohort("Areeb");
        oldCohort.setCohortId(id);
        // will be saved back to db and returned by method
        Cohort updatedCohort = new Cohort("Areeb");
        updatedCohort.setCohortId(id);

        // WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(oldCohort));
        Mockito.when(this.repo.save(updatedCohort)).thenReturn(updatedCohort);

        // THEN
        assertThat(this.service.editCohort(id, newCohort)).isEqualToComparingOnlyGivenFields(updatedCohort,"name");

        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
        Mockito.verify(this.repo, Mockito.times(1)).save(updatedCohort);
    }
    @Test
    void testGetCohort() {
        // GIVEN
        Cohort cohort = new Cohort("Areeb");
        cohort.setCohortId(1L); // cohort object to match the one in cohort-data.sql
        List<Cohort> cohorts = new ArrayList<>();
        cohorts.add(cohort);

        // WHEN
        Mockito.when(this.repo.findAll()).thenReturn(cohorts);

        // THEN
        assertThat(this.service.getAllCohorts());

        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }
    @Test
    void testGetCohortById() {
        // GIVEN
        Long id = 1L;
        Cohort cohort = new Cohort("Areeb");
        cohort.setCohortId(id); // cohort object to match the one in cohort-data.sql
        List<Cohort> cohorts = new ArrayList<>();
        cohorts.add(cohort);

        // WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(cohort));

        // THEN
        assertThat(this.service.getCohortById(id));

        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
    }
}
