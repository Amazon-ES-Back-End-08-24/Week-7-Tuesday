package com.ironhack.week7tuesday.exercise.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@DiscriminatorValue("MANAGER")
@Table(name = "managers")
public class Manager extends Employee {
    private int numberOfTeams;

    public Manager(Long id, String name, double salary, AddressExercise address, int numberOfTeams) {
        super(id, name, salary, address);
        this.numberOfTeams = numberOfTeams;
    }

    public Manager(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public Manager(String name, double salary, AddressExercise address, int numberOfTeams) {
        super(name, salary, address);
        this.numberOfTeams = numberOfTeams;
    }

    public Manager() {

    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "numberOfTeams=" + numberOfTeams +
                '}';
    }
}
