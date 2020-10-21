package com.qa.sfia3.dto;

public class TrainerDTO {

    private Long trainerId;
    private String forename;
    private String surname;

    public TrainerDTO() {
    }

    public TrainerDTO(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public Long getTrainerId() { return trainerId; }

    public void setTrainerId(Long trainerId) { this.trainerId = trainerId; }

    public String getForename() { return forename; }

    public void setForename(String forename) { this.forename = forename; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }
}
