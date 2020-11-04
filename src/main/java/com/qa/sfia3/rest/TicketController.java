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

    @CrossOrigin
    @GetMapping("/viewAllTickets")
    public ResponseEntity<List<TicketDTO>> viewAllTickets() {
        return ResponseEntity.ok(this.ticketService.viewAllTickets());
    }

    @CrossOrigin
    @PostMapping("/addTicket")
    public ResponseEntity<TicketDTO> addTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<TicketDTO>(this.ticketService.addTicket(ticket), HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping("/deleteTicket/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
        return this.ticketService.deleteTicket(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @GetMapping("/getTicketById/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(this.ticketService.getTicketById(id));
    }

    @CrossOrigin
    @PutMapping("/editTicket/{id}")
    public ResponseEntity<TicketDTO> editTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ResponseEntity.ok(this.ticketService.editTicket(id, ticket));
    }

    @CrossOrigin
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<TicketDTO> updateStatus(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ResponseEntity.ok(this.ticketService.updateStatus(id, ticket));
    }

    @CrossOrigin
    @PutMapping("/editSolution/{id}")
    public ResponseEntity<TicketDTO> editSolution(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ResponseEntity.ok(this.ticketService.updateSolution(id, ticket));
    }

}
