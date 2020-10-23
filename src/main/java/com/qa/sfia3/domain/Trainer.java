package com.qa.sfia3.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trainer {

    @Id
    @GeneratedValue
    private Long trainerId;

    @Column
    private String forename;

    @Column
    private String surname;

    @ManyToMany
    @JoinTable(
            name="trainerCohort",
            joinColumns = @JoinColumn(name = "trainerId"),
            inverseJoinColumns = @JoinColumn(name = "cohortId"))
    private List<Cohort> cohorts = new ArrayList<>();

    public Trainer() {
    }

    public Trainer(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public Long getTrainerId() { return trainerId; }

    public void setTrainerId(Long trainerId) { this.trainerId = trainerId; }

    public String getForename() { return forename; }

    public void setForename(String forename) { this.forename = forename; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public List<Cohort> getCohorts() { return cohorts; }

    public void setCohorts(List<Cohort> cohorts) { this.cohorts = cohorts; }
}