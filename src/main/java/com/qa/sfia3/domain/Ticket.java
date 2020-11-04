package com.qa.sfia3.domain;

import com.qa.sfia3.dto.TicketDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Column
    private String solution;

    @ManyToOne(targetEntity = Trainee.class)
    private Trainee trainee;

    public Ticket() {
    }

    public Ticket(String title, String description, String topic, String solution) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.solution = solution;
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
  
    public String getSolution() { return solution; }

    public void setSolution(String solution) { this.solution = solution; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) &&
                Objects.equals(title, ticket.title) &&
                Objects.equals(description, ticket.description) &&
                Objects.equals(topic, ticket.topic) &&
                Objects.equals(localDateTime, ticket.localDateTime) &&
                Objects.equals(status, ticket.status) &&
                Objects.equals(trainee, ticket.trainee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, title, description, topic, localDateTime, status, trainee);
    }
}