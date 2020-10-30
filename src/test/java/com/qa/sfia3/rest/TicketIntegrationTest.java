package com.qa.sfia3.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.qa.sfia3.repo.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.sfia3.domain.Ticket;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:ticket-schema.sql",
        "classpath:ticket-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TicketIntegrationTest {
    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ObjectMapper mapper;
    private TestRestTemplate testRestTemplate;
    @Autowired
    TicketRepository ticketRepository;

    @Test
    void testCreate() throws Exception {
        Ticket newTicket = new Ticket("Areeb","Problems","Springboot");
        String requestBody = this.mapper.writeValueAsString(newTicket);
        RequestBuilder request = post("/addTicket").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Ticket savedTicket = new Ticket("Areeb","Problems","Springboot");
        savedTicket.setTicketId(1L);

        String resultBody = this.mapper.writeValueAsString(savedTicket);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Ticket ticketResult = this.mapper.readValue(reqBody, Ticket.class);
        assertThat(ticketResult).isEqualToIgnoringGivenFields(savedTicket, "localDateTime");
    }
    @Test
    void testEditTicket() throws Exception {
        Ticket newTicket = new Ticket("Areeb","Problems","Springboot");
        String requestBody = this.mapper.writeValueAsString(newTicket);
        RequestBuilder request = put("/editTicket/1").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().isAccepted();

        Ticket savedTicket = new Ticket("Areeb","Problems","Springboot");
        savedTicket.setTicketId(1L); 

        String resultBody = this.mapper.writeValueAsString(savedTicket);
        ResultMatcher checkBody = content().json(resultBody);

        this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
    }
    @Test
    void testDelete() throws Exception {
        RequestBuilder request = delete("/deleteTicket/1");

        ResultMatcher checkStatus = status().is(200);

        this.mockMVC.perform(request).andExpect(checkStatus);

//		ResultMatcher checkStatus2 = status().is(500);
//
//		this.mockMVC.perform(request).andExpect(checkStatus);
    }
    @Test
    void testRead() throws Exception {
        Ticket ticket = new Ticket("Areeb","Problems","Springboot");
        ticket.setTicketId(1L); // ticket object to match the one in ticket-data.sql
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        String responseBody = this.mapper.writeValueAsString(tickets);

        this.mockMVC.perform(get("/viewAllTickets")).andExpect(status().isOk()).andExpect(content().json(responseBody));
    }

    @Test
    @Sql("/test.sql")
    void getTicketByIdTest(){
        ResponseEntity<Ticket> response = testRestTemplate.getForEntity("/getTicketById/1",Ticket.class);
        assertEquals("beera", response.getBody().getTitle());
        assertEquals("solution", response.getBody().getDescription());
        assertEquals("spring", response.getBody().getTopic());
    }

}
