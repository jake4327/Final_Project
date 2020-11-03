package com.qa.sfia3.service;

import com.qa.sfia3.domain.Ticket;
import com.qa.sfia3.dto.TicketDTO;
import com.qa.sfia3.exceptions.TicketNotFoundException;
import com.qa.sfia3.repo.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

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
//        this.repo.deleteById(id);
//        return !this.repo.existsById(id);
//        if(this.repo.existsById(id)) {
//            throw new TicketNotFoundException();
//        }
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

    public TicketDTO updateStatus(Long id, Ticket ticket) {
        Ticket update = this.repo.findById(id).orElseThrow(TicketNotFoundException::new);
        update.setStatus(ticket.getStatus());
        return this.mapToDTO(this.repo.save(ticket));
    }

    public TicketDTO editTicket(Long id, Ticket ticket) {
        Optional<Ticket> optTicket = this.repo.findById(id);
        Ticket oldTicket = optTicket.orElseThrow(() -> new TicketNotFoundException());
//      Ticket update = this.repo.findById(id).orElseThrow(TicketNotFoundException::new);
        oldTicket.setTitle(ticket.getTitle());
        oldTicket.setDescription(ticket.getDescription());
        oldTicket.setTopic(ticket.getTopic());
        TicketDTO saved = this.mapToDTO(this.repo.save(oldTicket));
        return saved;
    }

    public TicketDTO getTicketById(Long id) {
        return this.mapToDTO(this.repo.findById(id).orElseThrow(TicketNotFoundException::new));
    }

}
