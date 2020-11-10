package com.qa.sfia3.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Trainer {

    @Id
    @GeneratedValue
    private Long trainerId;

    @Column
    private String forename;

    @Column
    private String surname;

    @Column
    private Boolean role = false;

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

    public Boolean getRole() { return role; }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public void addCohort(List<Cohort> newCohorts) {
        for(int i = 0; i < newCohorts.size(); i++) {
            cohorts.add(newCohorts.get(i));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(trainerId, trainer.trainerId) &&
                Objects.equals(forename, trainer.forename) &&
                Objects.equals(surname, trainer.surname) &&
                Objects.equals(cohorts, trainer.cohorts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerId, forename, surname, cohorts);
    }
}
