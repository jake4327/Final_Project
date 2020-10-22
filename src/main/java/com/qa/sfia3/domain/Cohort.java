package com.qa.sfia3.domain;

import javax.persistence.*;

@Entity
public class Cohort {

    @Id
    @GeneratedValue
    private Long cohortId;

    @Column
    private String name;

    @OneToOne(mappedBy="cohort")
    private Trainer trainer;

    public Cohort() {
    }

    public Cohort(String name) {
        this.name = name;
    }

    public Long getCohortId() { return cohortId; }

    public void setCohortId(Long cohortId) { this.cohortId = cohortId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
