package com.qa.sfia3.dto;

import java.util.ArrayList;
import java.util.List;

public class FK_TRCH_DTO {

    private List<TrainerDTO> trainers = new ArrayList<>();
    private List<CohortDTO> cohorts = new ArrayList<>();

    public FK_TRCH_DTO() {
    }

    public List<TrainerDTO> getTrainers() { return trainers; }

    public void setTrainers(List<TrainerDTO> trainers) { this.trainers = trainers; }

    public List<CohortDTO> getCohorts() { return cohorts; }

    public void setCohorts(List<CohortDTO> cohorts) { this.cohorts = cohorts; }
}
