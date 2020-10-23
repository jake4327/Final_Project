package com.qa.sfia3.dto;

import com.qa.sfia3.domain.Cohort;

import java.util.ArrayList;
import java.util.List;

public class TraineeDTO {

    private Long traineeId;
    private String forename;
    private String surname;
    private List<TicketDTO> tickets = new ArrayList<>();
    private Cohort cohort;

    public TraineeDTO() {
    }

    public TraineeDTO(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public Long getTraineeId() { return traineeId; }

    public void setTraineeId(Long traineeId) { this.traineeId = traineeId; }

    public String getForename() { return forename; }

    public void setForename(String forename) { this.forename = forename; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public List<TicketDTO> getTickets() { return tickets; }

    public void setTickets(List<TicketDTO> tickets) { this.tickets = tickets; }
}
