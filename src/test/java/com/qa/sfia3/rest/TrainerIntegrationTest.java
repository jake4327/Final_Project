package com.qa.sfia3.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.sfia3.domain.Trainer;
import com.qa.sfia3.repo.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TrainerIntegrationTest {
    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ObjectMapper mapper;
    private TestRestTemplate testRestTemplate;
    @Autowired
    TrainerRepository trainerRepository;

    @Test
    void testAddTrainer() throws Exception {
        Trainer newTrainer = new Trainer("Areeb","Panjwani");
        String requestBody = this.mapper.writeValueAsString(newTrainer);
        RequestBuilder request = post("/addTrainer").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Trainer savedTrainer = new Trainer("Areeb","Panjwani");
        savedTrainer.setTrainerId(1L);

        String resultBody = this.mapper.writeValueAsString(savedTrainer);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Trainer trainerResult = this.mapper.readValue(reqBody, Trainer.class);
        assertThat(trainerResult).isEqualToComparingOnlyGivenFields(savedTrainer, "forename");
    }
    @Test
//   @Sql("/test.sql")
    void testEditTrainer() throws Exception {
        //add a trainer
        Trainer oldTrainer = new Trainer("Areeb","Panjwani");
        String requestBody = this.mapper.writeValueAsString(oldTrainer);
        RequestBuilder request = post("/addTrainer").contentType(MediaType.APPLICATION_JSON).content(requestBody);
        ResultMatcher checkStatus = status().is(201);
        Trainer savedTrainer = new Trainer("Areeb","Panjwani");
        savedTrainer.setTrainerId(1L);
        String resultBody = this.mapper.writeValueAsString(savedTrainer);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Trainer trainerResult = this.mapper.readValue(reqBody, Trainer.class);
        assertThat(trainerResult).isEqualToComparingOnlyGivenFields(savedTrainer, "forename");

        // edit the trainer that was just added
        Trainer newTrainer = new Trainer("Areeb","Panjwani");
        String trainerValue = this.mapper.writeValueAsString(newTrainer);
        RequestBuilder ask = put("/editTrainer/1").contentType(MediaType.APPLICATION_JSON).content(trainerValue);
//        ResultMatcher Status = status().isAccepted();
        ResultMatcher Status = status().is(200);
        Trainer updatedTrainer = new Trainer("Areeb","Panjwani");
        updatedTrainer.setTrainerId(1L);

        String results = this.mapper.writeValueAsString(updatedTrainer);
        ResultMatcher check = content().json(results);
        this.mockMVC.perform(ask).andExpect(Status);
        MvcResult res = this.mockMVC.perform(ask).andExpect(Status).andReturn();
        String req = res.getResponse().getContentAsString();
        Trainer trainerR = this.mapper.readValue(reqBody, Trainer.class);
        assertThat(trainerR).isEqualToComparingOnlyGivenFields(updatedTrainer, "forename");
    }

    @Test
//    @Sql("/test.sql")
    void testGetTrainers() throws Exception {
        Trainer newTrainer = new Trainer("Areeb","Panjwani");
        String requestBody = this.mapper.writeValueAsString(newTrainer);
        RequestBuilder request = post("/addTrainer").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Trainer savedTrainer = new Trainer("Areeb","Panjwani");
        savedTrainer.setTrainerId(1L);

        String resultBody = this.mapper.writeValueAsString(savedTrainer);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Trainer trainerResult = this.mapper.readValue(reqBody, Trainer.class);
        assertThat(trainerResult).isEqualToComparingOnlyGivenFields(savedTrainer, "forename");

        //list trainers
        Trainer trainer = new Trainer("Areeb","Panjwani");
        trainer.setTrainerId(1L); // trainer object to match the one in trainer-data.sql
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(trainer);
        String responseBody = this.mapper.writeValueAsString(trainers);

        this.mockMVC.perform(get("/getAllTrainers")).andExpect(status().isOk());
    }
    @Test
//    @Sql("/test.sql")
    void testGetTrainerById() throws Exception {
        Trainer newTrainer = new Trainer("Areeb","Panjwani");
        String requestBody = this.mapper.writeValueAsString(newTrainer);
        RequestBuilder request = post("/addTrainer").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Trainer savedTrainer = new Trainer("Areeb","Panjwani");
        savedTrainer.setTrainerId(1L);

        String resultBody = this.mapper.writeValueAsString(savedTrainer);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Trainer trainerResult = this.mapper.readValue(reqBody, Trainer.class);
        assertThat(trainerResult).isEqualToComparingOnlyGivenFields(savedTrainer, "forename");

        //list trainers
        Trainer trainer = new Trainer("Areeb","Panjwani");
        trainer.setTrainerId(1L); // trainer object to match the one in trainer-data.sql
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(trainer);
        String responseBody = this.mapper.writeValueAsString(trainers);

        this.mockMVC.perform(get("/getTrainerById/1")).andExpect(status().isOk());
    }

}
