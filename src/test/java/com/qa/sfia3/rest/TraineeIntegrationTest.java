package com.qa.sfia3.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.sfia3.domain.Trainee;
import com.qa.sfia3.domain.Cohort;
import com.qa.sfia3.repo.TraineeRepository;
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
public class TraineeIntegrationTest {
    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ObjectMapper mapper;
    private TestRestTemplate testRestTemplate;
    @Autowired
    TraineeRepository traineeRepository;

    @Test
    void testAddTrainee() throws Exception {
        Cohort newCohort = new Cohort("Areeb");
        String requestCo = this.mapper.writeValueAsString(newCohort);
        RequestBuilder ask = post("/addCohort").contentType(MediaType.APPLICATION_JSON).content(requestCo);

        ResultMatcher Status = status().is(201);

        Cohort savedCohort = new Cohort("Areeb");
        savedCohort.setCohortId(6L);

        String results = this.mapper.writeValueAsString(savedCohort);
        ResultMatcher checkBody = content().json(results);
        MvcResult answer = this.mockMVC.perform(ask).andExpect(Status).andReturn();
        // In case you need to access the actual result as an object:
        String req = answer.getResponse().getContentAsString();

        Cohort cohortResult = this.mapper.readValue(req, Cohort.class);
        assertThat(cohortResult).isEqualToComparingOnlyGivenFields(savedCohort, "name");;

        //add a trainee
        Trainee  newTrainee = new Trainee(1,"Areeb","Panjwani");
        String requestBody = this.mapper.writeValueAsString(newTrainee);
        RequestBuilder request = post("/addTrainee").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Trainee savedTrainee = new Trainee("Areeb","Panjwani");
        savedTrainee.setTraineeId(6L);
        savedTrainee.setCohort(savedCohort);

        String resultBody = this.mapper.writeValueAsString(savedTrainee);
        ResultMatcher check = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Trainee traineeResult = this.mapper.readValue(reqBody, Trainee.class);
        assertThat(traineeResult).isEqualTo(savedTrainee);
    }
    @Test
//   @Sql("/test.sql")
    void testEditTrainee() throws Exception {
        //add a trainee
        Trainee oldTrainee = new Trainee("Areeb","Panjwani");
        String requestBody = this.mapper.writeValueAsString(oldTrainee);
        RequestBuilder request = post("/addTrainee").contentType(MediaType.APPLICATION_JSON).content(requestBody);
        ResultMatcher checkStatus = status().is(201);
        Trainee savedTrainee = new Trainee("Areeb","Panjwani");
        savedTrainee.setTraineeId(1L);
        String resultBody = this.mapper.writeValueAsString(savedTrainee);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Trainee traineeResult = this.mapper.readValue(reqBody, Trainee.class);
        assertThat(traineeResult).isEqualToComparingOnlyGivenFields(savedTrainee, "forename");

        // edit the trainee that was just added
        Trainee newTrainee = new Trainee("Areeb","Panjwani");
        String traineeValue = this.mapper.writeValueAsString(newTrainee);
        RequestBuilder ask = put("/editTrainee/1").contentType(MediaType.APPLICATION_JSON).content(traineeValue);
//        ResultMatcher Status = status().isAccepted();
        ResultMatcher Status = status().is(200);
        Trainee updatedTrainee = new Trainee("Areeb","Panjwani");
        updatedTrainee.setTraineeId(1L);

        String results = this.mapper.writeValueAsString(updatedTrainee);
        ResultMatcher check = content().json(results);
        this.mockMVC.perform(ask).andExpect(Status);
        MvcResult res = this.mockMVC.perform(ask).andExpect(Status).andReturn();
        String req = res.getResponse().getContentAsString();
        Trainee traineeR = this.mapper.readValue(reqBody, Trainee.class);
        assertThat(traineeR).isEqualToComparingOnlyGivenFields(updatedTrainee, "forename");
    }

    @Test
//    @Sql("/test.sql")
    void testGetTrainees() throws Exception {
        Trainee newTrainee = new Trainee("Areeb","Panjwani");
        String requestBody = this.mapper.writeValueAsString(newTrainee);
        RequestBuilder request = post("/addTrainee").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Trainee savedTrainee = new Trainee("Areeb","Panjwani");
        savedTrainee.setTraineeId(1L);

        String resultBody = this.mapper.writeValueAsString(savedTrainee);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Trainee traineeResult = this.mapper.readValue(reqBody, Trainee.class);
        assertThat(traineeResult).isEqualToComparingOnlyGivenFields(savedTrainee, "forename");

        //list trainees
        Trainee trainee = new Trainee("Areeb","Panjwani");
        trainee.setTraineeId(1L); // trainee object to match the one in trainee-data.sql
        List<Trainee> trainees = new ArrayList<>();
        trainees.add(trainee);
        String responseBody = this.mapper.writeValueAsString(trainees);

        this.mockMVC.perform(get("/getAllTrainees")).andExpect(status().isOk());
    }
}
