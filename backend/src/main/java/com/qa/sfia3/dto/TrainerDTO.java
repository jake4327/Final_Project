package com.qa.sfia3.dto;

import java.util.ArrayList;
import java.util.List;

public class TrainerDTO {

    private Long trainerId;
    private String forename;
    private String surname;
    private List<CohortDTO> cohorts = new ArrayList<>();
    private Boolean role;

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

    public List<CohortDTO> getCohorts() { return cohorts; }

    public void setCohorts(List<CohortDTO> cohorts) { this.cohorts = cohorts; }

    public Boolean getRole() { return role; }

    public void setRole(Boolean role) { this.role = role; }
}
