package com.ironhack.week7tuesday.exercise.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@DiscriminatorValue("ENGINEER")
@Table(name = "engineers")
public class Engineer extends Employee {
    private String speciality;

    public Engineer(Long id, String name, double salary, AddressExercise address, String speciality) {
        super(id, name, salary, address);
        this.speciality = speciality;
    }

    public Engineer(String speciality) {
        this.speciality = speciality;
    }

    public Engineer(String name, double salary, AddressExercise address, String speciality) {
        super(name, salary, address);
        this.speciality = speciality;
    }

    public Engineer() {

    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Engineer{" +
                "speciality='" + speciality + '\'' +
                '}';
    }
}
