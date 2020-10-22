package com.qa.sfia3.dto;

import java.util.ArrayList;
import java.util.List;

public class CohortDTO {

    private Long cohortId;
    private String name;
    private List<TraineeDTO> trainees = new ArrayList<>();
    private List<TrainerDTO> trainers = new ArrayList<>();

    public CohortDTO() {
    }

    public CohortDTO(String name) {
        this.name = name;
    }

    public Long getCohortId() { return cohortId; }

    public void setCohortId(Long cohortId) { this.cohortId = cohortId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<TraineeDTO> getTrainees() { return trainees; }

    public void setTrainees(List<TraineeDTO> trainees) { this.trainees = trainees; }

    public List<TrainerDTO> getTrainers() { return trainers; }

    public void setTrainers(List<TrainerDTO> trainers) { this.trainers = trainers; }
}
