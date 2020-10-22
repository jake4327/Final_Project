package com.qa.sfia3.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cohort {

    @Id
    @GeneratedValue
    private Long cohortId;

    @Column
    private String name;

    @ManyToMany(mappedBy = "cohorts")
    private List<Trainer> trainers = new ArrayList<>();

    public Cohort() {
    }

    public Cohort(String name) {
        this.name = name;
    }

    public Long getCohortId() { return cohortId; }

    public void setCohortId(Long cohortId) { this.cohortId = cohortId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Trainer> getTrainers() { return trainers; }

    public void setTrainers(List<Trainer> trainers) { this.trainers = trainers; }
}
