package com.qa.sfia3.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.sfia3.domain.Ticket;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class TickerIntegrationTest {
    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
        Ticket newTicket = new Ticket("Areeb","Problems","Springboot");
        String requestBody = this.mapper.writeValueAsString(newTicket);
        RequestBuilder request = post("/addTicket").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Ticket savedTicket = new Ticket("Areeb","Problems","Springboot");
        savedTicket.setId(2L);

        String resultBody = this.mapper.writeValueAsString(savedTicket);
        ResultMatcher checkBody = content().json(resultBody);

        this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();

        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Ticket ticketResult = this.mapper.readValue(reqBody, Ticket.class);
    }
    @Test
    void testUpdate() throws Exception {
        Ticket newTicket = new Ticket("yoyo","ayy","idunno");
        String requestBody = this.mapper.writeValueAsString(newTicket);
        RequestBuilder request = put("/updateStatus?id=1").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().isAccepted();

        Ticket savedTicket = new Ticket("yoyo","ayy","idunno");
        savedTicket.setId(1L); // id = 1 because we're updating the value inserted using data.sql

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
        Ticket ticket = new Ticket("Areeb","Problems","springboot");
        ticket.setId(1L); // ticket object to match the one in ticket-data.sql
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        String responseBody = this.mapper.writeValueAsString(tickets);

        this.mockMVC.perform(get("/viewAllTickets")).andExpect(status().isOk()).andExpect(content().json(responseBody));
    }
}
