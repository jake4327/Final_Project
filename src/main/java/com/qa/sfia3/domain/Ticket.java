package com.qa.sfia3.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private Long ticketId;

    @Column
    private String title;

    @Column
    private String description;

    @Column()
    private java.time.LocalDateTime localDateTime = LocalDateTime.now();

    @Column
    private String trainee;

    @Column()
    private Boolean status = false;

    public Ticket() {
    }

    public Ticket(String title, String description, String trainee) {
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

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDateTime getLocalDateTime() { return localDateTime; }

    public void setLocalDateTime(LocalDateTime localDateTime) { this.localDateTime = localDateTime; }
}