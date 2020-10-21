package com.qa.sfia3.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FK_TRCH {

    @Id
    @OneToMany(mappedBy = "trch", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Trainer> trainers = new ArrayList<>();

    @Id
    @OneToMany(mappedBy = "trch", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Cohort> cohorts = new ArrayList<>();

    public FK_TRCH() {
    }

    public List<Trainer> getTrainers() { return trainers; }

    public void setTrainers(List<Trainer> trainers) { this.trainers = trainers; }

    public List<Cohort> getCohorts() { return cohorts; }

    public void setCohorts(List<Cohort> cohorts) { this.cohorts = cohorts; }
}
