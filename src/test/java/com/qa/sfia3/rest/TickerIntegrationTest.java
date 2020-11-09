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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TickerIntegrationTest {
    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ObjectMapper mapper;
    private TestRestTemplate testRestTemplate;
    @Autowired
    TicketRepository ticketRepository;

    @Test
    void testAddTicket() throws Exception {
        Ticket newTicket = new Ticket("Areeb","Problems","Springboot", "default solution");
        String requestBody = this.mapper.writeValueAsString(newTicket);
        RequestBuilder request = post("/addTicket").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Ticket savedTicket = new Ticket("Areeb","Problems","Springboot", "default solution");
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
//   @Sql("/test.sql")
    void testEditTicket() throws Exception {
        //add a ticket
        Ticket oldTicket = new Ticket("Areeb","Problems","Springboot", "default solution");
        String requestBody = this.mapper.writeValueAsString(oldTicket);
        RequestBuilder request = post("/addTicket").contentType(MediaType.APPLICATION_JSON).content(requestBody);
        ResultMatcher checkStatus = status().is(201);
        Ticket savedTicket = new Ticket("Areeb","Problems","Springboot", "default solution");
        savedTicket.setTicketId(1L);
        String resultBody = this.mapper.writeValueAsString(savedTicket);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Ticket ticketResult = this.mapper.readValue(reqBody, Ticket.class);
        assertThat(ticketResult).isEqualToIgnoringGivenFields(savedTicket, "localDateTime");

        // edit the ticket that was just added
        Ticket newTicket = new Ticket("Areeb","Problems","Springboot", "default solution");
        String ticketValue = this.mapper.writeValueAsString(newTicket);
        RequestBuilder ask = put("/editTicket/1").contentType(MediaType.APPLICATION_JSON).content(ticketValue);
//        ResultMatcher Status = status().isAccepted();
        ResultMatcher Status = status().is(200);
        Ticket updatedTicket = new Ticket("Areeb","Problems","Springboot", "default solution");
        updatedTicket.setTicketId(1L);

        String results = this.mapper.writeValueAsString(updatedTicket);
        ResultMatcher check = content().json(results);
        this.mockMVC.perform(ask).andExpect(Status);
        MvcResult res = this.mockMVC.perform(ask).andExpect(Status).andReturn();
        String req = res.getResponse().getContentAsString();
        Ticket ticketR = this.mapper.readValue(reqBody, Ticket.class);
        assertThat(ticketR).isEqualToIgnoringGivenFields(updatedTicket, "localDateTime");
    }

    @Test
    void testDelete() throws Exception {
        Ticket newTicket = new Ticket("Areeb","Problems","Springboot", "default");
        String requestBody = this.mapper.writeValueAsString(newTicket);
        RequestBuilder request = post("/addTicket").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Ticket savedTicket = new Ticket("Areeb","Problems","Springboot", "default");
        savedTicket.setTicketId(1L);

        String resultBody = this.mapper.writeValueAsString(savedTicket);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Ticket ticketResult = this.mapper.readValue(reqBody, Ticket.class);
        assertThat(ticketResult).isEqualToIgnoringGivenFields(savedTicket, "localDateTime");


        RequestBuilder ask = delete("/deleteTicket/1");

        ResultMatcher Status = status().is(200);

        this.mockMVC.perform(ask).andExpect(Status);

//		ResultMatcher checkStatus2 = status().is(500);
//
//		this.mockMVC.perform(request).andExpect(checkStatus);
    }
    @Test
//    @Sql("/test.sql")
    void testViewAllTickets() throws Exception {
        Ticket newTicket = new Ticket("Areeb","Problems","Springboot", "default");
        String requestBody = this.mapper.writeValueAsString(newTicket);
        RequestBuilder request = post("/addTicket").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Ticket savedTicket = new Ticket("Areeb","Problems","Springboot", "default");
        savedTicket.setTicketId(1L);

        String resultBody = this.mapper.writeValueAsString(savedTicket);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Ticket ticketResult = this.mapper.readValue(reqBody, Ticket.class);
        assertThat(ticketResult).isEqualToIgnoringGivenFields(savedTicket, "localDateTime");

        //list tickets
        Ticket ticket = new Ticket("Areeb","Problems","Springboot", "default");
        ticket.setTicketId(1L); // ticket object to match the one in ticket-data.sql
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        String responseBody = this.mapper.writeValueAsString(tickets);

        this.mockMVC.perform(get("/viewAllTickets")).andExpect(status().isOk());
    }

    

    @Test
    void testUpdateStatus() throws Exception {
        //add a ticket
        Ticket oldTicket = new Ticket("yoyo","ayy","idunno", "default");
        String requestBody = this.mapper.writeValueAsString(oldTicket);
        RequestBuilder request = post("/addTicket").contentType(MediaType.APPLICATION_JSON).content(requestBody);
        ResultMatcher checkStatus = status().is(201);
        Ticket savedTicket = new Ticket("yoyo","ayy","idunno", "default");
        savedTicket.setTicketId(1L);
        String resultBody = this.mapper.writeValueAsString(savedTicket);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Ticket ticketResult = this.mapper.readValue(reqBody, Ticket.class);
        assertThat(ticketResult).isEqualToIgnoringGivenFields(savedTicket, "localDateTime");

        // edit the ticket that was just added
        Ticket newTicket = new Ticket("yoyo","ayy","idunno", "default");
        String ticketValue = this.mapper.writeValueAsString(newTicket);
        RequestBuilder ask = put("/updateStatus/1").contentType(MediaType.APPLICATION_JSON).content(ticketValue);
//        ResultMatcher Status = status().isAccepted();
        ResultMatcher Status = status().is(200);
        Ticket updatedTicket = new Ticket("yoyo","ayy","idunno", "default");
        updatedTicket.setTicketId(1L);

        String results = this.mapper.writeValueAsString(updatedTicket);
        ResultMatcher check = content().json(results);
        this.mockMVC.perform(ask).andExpect(Status);
        MvcResult res = this.mockMVC.perform(ask).andExpect(Status).andReturn();
        String req = res.getResponse().getContentAsString();
        Ticket ticketR = this.mapper.readValue(reqBody, Ticket.class);
        assertThat(ticketR).isEqualToIgnoringGivenFields(updatedTicket, "localDateTime");
    }
    @Test
//    @Sql("/test.sql")
    void testGetTicketById() throws Exception {
        Ticket newTicket = new Ticket("Areeb","Problems","Springboot", "default");
        String requestBody = this.mapper.writeValueAsString(newTicket);
        RequestBuilder request = post("/addTicket").contentType(MediaType.APPLICATION_JSON).content(requestBody);

        ResultMatcher checkStatus = status().is(201);

        Ticket savedTicket = new Ticket("Areeb","Problems","Springboot", "default");
        savedTicket.setTicketId(1L);

        String resultBody = this.mapper.writeValueAsString(savedTicket);
        ResultMatcher checkBody = content().json(resultBody);
        MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();
        // In case you need to access the actual result as an object:
        String reqBody = result.getResponse().getContentAsString();

        Ticket ticketResult = this.mapper.readValue(reqBody, Ticket.class);
        assertThat(ticketResult).isEqualToIgnoringGivenFields(savedTicket, "localDateTime");

        //list tickets
        Ticket ticket = new Ticket("Areeb","Problems","Springboot", "default");
        ticket.setTicketId(1L); // ticket object to match the one in ticket-data.sql
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        String responseBody = this.mapper.writeValueAsString(tickets);

        this.mockMVC.perform(get("/getTicketById/1")).andExpect(status().isOk());
    }
}
