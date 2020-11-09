package com.qa.sfia3.dto;

import java.time.LocalDateTime;

public class TicketDTO {

    private Long ticketId;
    private String title;
    private String description;
    private String topic;
    private java.time.LocalDateTime localDateTime;
    private Boolean status;
    private String solution;

    public TicketDTO() {
    }

    public TicketDTO(String title, String description, String topic, String solution) {
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getLocalDateTime() { return localDateTime; }

    public void setLocalDateTime(LocalDateTime localDateTime) { this.localDateTime = localDateTime; }

    public String getSolution() { return solution; }

    public void setSolution(String solution) { this.solution = solution; }
}