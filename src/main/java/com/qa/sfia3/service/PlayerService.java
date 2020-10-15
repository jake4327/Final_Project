package com.qa.sfia3.service;

import com.qa.sfia3.domain.Ticket;
import com.qa.sfia3.dto.TicketDTO;
import com.qa.sfia3.exceptions.TicketNotFoundException;
import com.qa.sfia3.repo.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final TicketRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public TicketService(TicketRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private TicketDTO mapToDTO(Ticket ticket) {
        return this.mapper.map(ticket, TicketDTO.class);
    }

    public List<TicketDTO> viewAllTickets() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TicketDTO addTicket(Ticket ticket) {
        return this.mapToDTO(this.repo.save(ticket));
    }

    public Boolean deleteTicket(Long id) {
        if(!this.repo.existsById(id)) {
            throw new TicketNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

    public TicketDTO updateStatus(Long id, Ticket ticket) {
        Ticket update = this.repo.findById(id).orElseThrow(TicketNotFoundException::new);
        update.setStatus(ticket.getStatus());
    }

    public TicketDTO editTicket(Long id, Ticket ticket) {
        Ticket update = this.repo.findById(id).orElseThrow(TicketNotFoundException::new);
        update.setTitle(ticket.getTitle());
        update.setDescription(ticket.getDescription());
        update.setTrainee(ticket.getTrainee());
        return this.mapToDTO(this.repo.save(ticket));
    }

    public Date getDate(Long id, Ticket ticket) {
        if(!this.repo.existsById(id)) {
            throw new TicketNotFoundException();
        }
        return ticket.getTimestamp();
    }

}
