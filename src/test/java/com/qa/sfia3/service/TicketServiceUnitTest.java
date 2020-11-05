package com.qa.sfia3.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.assertj.core.api.InstanceOfAssertFactories.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.qa.sfia3.domain.Ticket;
import com.qa.sfia3.dto.TicketDTO;
import com.qa.sfia3.repo.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest
@ActiveProfiles(profiles = "test")
public class TicketServiceUnitTest {

    @Autowired
    private TicketService service;

    @MockBean
    private TicketRepository repo;

    @Test
    void testCreate(){
        //Given
        Long id = 1L;
        Ticket newTicket = new Ticket ("Areeb","Problems","Springboot","default");
        Ticket savedTicket = new Ticket("Areeb","Problems","Springboot","default");
        savedTicket.setTicketId(id);
        //When
        Mockito.when(this.repo.save(newTicket)).thenReturn(savedTicket);
        //then
        assertThat(this.service.addTicket(newTicket)).isEqualToIgnoringGivenFields(savedTicket, "localDateTime");

        Mockito.verify(this.repo, Mockito.times(1)).save(newTicket);
    }

    @Test
    void testDelete() {
        // GIVEN
        Long id = 1L;
        boolean found = false;

        // WHEN
       Mockito.when(this.repo.existsById(id)).thenReturn(found);

        // THEN
        assertThat(this.service.deleteTicket(id)).isEqualTo(!found);

        Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
    }
    @Test
    void testGet() {
        // GIVEN
        Ticket ticket = new Ticket("Areeb","Problems","Springboot","default");
        ticket.setTicketId(1L); // ticket object to match the one in ticket-data.sql
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        // WHEN
        Mockito.when(this.repo.findAll()).thenReturn(tickets);

        // THEN
        assertThat(this.service.viewAllTickets()).isNotEmpty();

        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }
//    @Test
//    void testEditTicket(){
//        //Given
//        Long id = 1L;
//        //Will be passed in
//        Ticket newTicket = new Ticket("Areeb","Problems","Springboot","default");
//        //will be findById()
//        Ticket oldTicket = new Ticket("Areeb","Problems","Springboot","default");
//        oldTicket.setTicketId(id);
//        //will be saved back to db and returned by method
//        Ticket updatedTicket = new Ticket("Areeb","Problems","Springboot","default");
//        updatedTicket.setTicketId(id);
//
//        //When
//        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(oldTicket));
//        Mockito.when(this.repo.save(updatedTicket)).thenReturn(updatedTicket);
//
//        //Then
//        assertThat(this.service.editTicket(id, newTicket)).isEqualToIgnoringGivenFields(updatedTicket,"localDateTime");
//
//        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
//        Mockito.verify(this.repo, Mockito.times(1)).save(updatedTicket);
//    }
    @Test
    void testGetTicketById() {
        // GIVEN
        Long id = 1L;
        Ticket ticket = new Ticket("Areeb","Problems","Springboot","default");
        ticket.setTicketId(id); // ticket object to match the one in ticket-data.sql
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        // WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(ticket));

        // THEN
        assertThat(this.service.getTicketById(id));

        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
    }
}
