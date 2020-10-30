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
    private String topic;

    @Column()
    private java.time.LocalDateTime localDateTime = LocalDateTime.now();

    @Column()
    private Boolean status = false;

    @ManyToOne(targetEntity = Trainee.class)
    private Trainee trainee;

    public Ticket() {
    }

    public Ticket(String title, String description, String topic) {
        this.title = title;
        this.description = description;
        this.topic = topic;
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

    public String getTopic() { return topic; }

    public void setTopic(String topic) { this.topic = topic; }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getLocalDateTime() { return localDateTime; }

    public void setLocalDateTime(LocalDateTime localDateTime) { this.localDateTime = localDateTime; }

    public Trainee getTrainee() { return trainee; }

    public void setTrainee(Trainee trainee) { this.trainee = trainee; }

}