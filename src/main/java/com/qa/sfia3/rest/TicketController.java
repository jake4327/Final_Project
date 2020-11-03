package com.qa.sfia3.rest;

import com.qa.sfia3.domain.Ticket;
import com.qa.sfia3.dto.TicketDTO;
import com.qa.sfia3.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/viewAllTickets")
    public ResponseEntity<List<TicketDTO>> viewAllTickets() {
        return ResponseEntity.ok(this.ticketService.viewAllTickets());
    }

    @PostMapping("/addTicket")
    public ResponseEntity<TicketDTO> addTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<TicketDTO>(this.ticketService.addTicket(ticket), HttpStatus.CREATED);
    }

//    @DeleteMapping("/deleteTicket/{id}")
//    public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
//        return this.ticketService.deleteTicket(id)
//                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
//                : ResponseEntity.noContent().build();
//    }
    @DeleteMapping("/deleteTicket/{id}")
    public ResponseEntity<Object> deleteWood(@PathVariable Long id) {
        if (this.ticketService.deleteTicket(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTicketById/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(this.ticketService.getTicketById(id));
    }

    @PutMapping("/editTicket/{id}")
    public ResponseEntity<TicketDTO> editTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ResponseEntity.ok(this.ticketService.editTicket(id, ticket));
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<TicketDTO> updateStatus(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ResponseEntity.ok(this.ticketService.updateStatus(id, ticket));
    }

}
