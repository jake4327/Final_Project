package com.qa.sfia3.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Trainee {

    @Id
    @GeneratedValue
    private Long traineeId;

    @Column
    private String forename;

    @Column
    private String surname;

    @OneToMany(mappedBy = "trainee", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name="cohortId", nullable = false)
    private Cohort cohort;

    public Trainee() {
    }

    public Trainee(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public Long getTraineeId() { return traineeId; }

    public void setTraineeId(Long traineeId) { this.traineeId = traineeId; }

    public String getForename() { return forename; }

    public void setForename(String forename) { this.forename = forename; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public List<Ticket> getTickets() { return tickets; }

    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }

    public Cohort getCohort() { return cohort; }

    public void setCohort(Cohort cohort) { this.cohort = cohort; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainee trainee = (Trainee) o;
        return Objects.equals(traineeId, trainee.traineeId) &&
                Objects.equals(forename, trainee.forename) &&
                Objects.equals(surname, trainee.surname) &&
                Objects.equals(tickets, trainee.tickets) &&
                Objects.equals(cohort, trainee.cohort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traineeId, forename, surname, tickets, cohort);
    }
}
