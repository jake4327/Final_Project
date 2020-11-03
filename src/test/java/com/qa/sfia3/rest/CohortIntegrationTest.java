package com.qa.sfia3.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.sfia3.domain.Cohort;
import com.qa.sfia3.repo.CohortRepository;
import com.qa.sfia3.repo.CohortRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CohortIntegrationTest {
    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ObjectMapper mapper;
    private TestRestTemplate testRestTemplate;
    @Autowired
    CohortRepository cohortRepository;

    @Test
    void testAddCohort() throws Exception {
        Cohort newCohort = new Cohort("Areeb");
        String requestBody = this.mapper.writeValueAsString(newCohort);
        RequestBuilder request = post("/addCohort").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Cohort savedCohort = new Cohort("Areeb");
        savedCohort.setCohortId(6L);

        String resultBody = this.mapper.writeValueAsString(savedCohort);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Cohort cohortResult = this.mapper.readValue(reqBody, Cohort.class);
        assertThat(cohortResult).isEqualToComparingOnlyGivenFields(savedCohort, "name");
    }
    @Test
//   @Sql("/test.sql")
    void testEditCohort() throws Exception {
        //add a cohort
        Cohort oldCohort = new Cohort("yoyo");
        String requestBody = this.mapper.writeValueAsString(oldCohort);
        RequestBuilder request = post("/addCohort").contentType(MediaType.APPLICATION_JSON).content(requestBody);
        ResultMatcher checkStatus = status().is(201);
        Cohort savedCohort = new Cohort("yoyo");
        savedCohort.setCohortId(1L);
        String resultBody = this.mapper.writeValueAsString(savedCohort);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Cohort cohortResult = this.mapper.readValue(reqBody, Cohort.class);
        assertThat(cohortResult).isEqualToComparingOnlyGivenFields(savedCohort, "name");

        // edit the cohort that was just added
        Cohort newCohort = new Cohort("yoyo");
        String cohortValue = this.mapper.writeValueAsString(newCohort);
        RequestBuilder ask = put("/editCohort/1").contentType(MediaType.APPLICATION_JSON).content(cohortValue);
//        ResultMatcher Status = status().isAccepted();
        ResultMatcher Status = status().is(200);
        Cohort updatedCohort = new Cohort("yoyo");
        updatedCohort.setCohortId(1L);

        String results = this.mapper.writeValueAsString(updatedCohort);
        ResultMatcher check = content().json(results);
        this.mockMVC.perform(ask).andExpect(Status);
        MvcResult res = this.mockMVC.perform(ask).andExpect(Status).andReturn();
        String req = res.getResponse().getContentAsString();
        Cohort cohortR = this.mapper.readValue(reqBody, Cohort.class);
        assertThat(cohortR).isEqualToComparingOnlyGivenFields(updatedCohort, "name");
    }

    @Test
//    @Sql("/test.sql")
    void testGetCohorts() throws Exception {
        Cohort newCohort = new Cohort("Areeb");
        String requestBody = this.mapper.writeValueAsString(newCohort);
        RequestBuilder request = post("/addCohort").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Cohort savedCohort = new Cohort("Areeb");
        savedCohort.setCohortId(1L);

        String resultBody = this.mapper.writeValueAsString(savedCohort);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Cohort cohortResult = this.mapper.readValue(reqBody, Cohort.class);
        assertThat(cohortResult).isEqualToComparingOnlyGivenFields(savedCohort, "name");

        //list cohorts
        Cohort cohort = new Cohort("Areeb");
        cohort.setCohortId(1L); // cohort object to match the one in cohort-data.sql
        List<Cohort> cohorts = new ArrayList<>();
        cohorts.add(cohort);
        String responseBody = this.mapper.writeValueAsString(cohorts);

        this.mockMVC.perform(get("/getAllCohorts")).andExpect(status().isOk());
    }
    @Test
//    @Sql("/test.sql")
    void testGetCohortById() throws Exception {
        Cohort newCohort = new Cohort("Areeb");
        String requestBody = this.mapper.writeValueAsString(newCohort);
        RequestBuilder request = post("/addCohort").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Cohort savedCohort = new Cohort("Areeb");
        savedCohort.setCohortId(1L);

        String resultBody = this.mapper.writeValueAsString(savedCohort);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Cohort cohortResult = this.mapper.readValue(reqBody, Cohort.class);
        assertThat(cohortResult).isEqualToComparingOnlyGivenFields(savedCohort, "name");

        //list cohorts
        Cohort cohort = new Cohort("Areeb");
        cohort.setCohortId(1L); // cohort object to match the one in cohort-data.sql
        List<Cohort> cohorts = new ArrayList<>();
        cohorts.add(cohort);
        String responseBody = this.mapper.writeValueAsString(cohorts);

        this.mockMVC.perform(get("/getCohortById/1")).andExpect(status().isOk());
    }
}
