package com.qa.sfia3.dto;

import java.util.Date;

public class TicketDTO {
    private Long ticketId;
    private String title;
    private String description;
    private Date timestamp;
    private String trainee;
    private Boolean status;

    public TicketDTO() {
    }

    public TicketDTO(String title, String description, String trainee) {
        this.title = title;
        this.description = description;
        this.trainee = trainee;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTrainee() {
        return trainee;
    }

    public void setTrainee(String trainee) {
        this.trainee = trainee;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}