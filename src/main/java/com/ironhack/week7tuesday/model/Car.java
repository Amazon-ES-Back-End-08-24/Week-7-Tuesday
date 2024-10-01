package com.ironhack.week7tuesday.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@DiscriminatorValue("CAR")
@Table(name = "cars") // <-- NO con SINGLE_TABLE
public class Car extends Vehicle {

    private int seats;

    public Car(Long id, String brand, int seats) {
        super(id, brand);
        this.seats = seats;
    }

    public Car(String brand, int seats) {
        super(brand);
        this.seats = seats;
    }

    public Car(int seats) {
        this.seats = seats;
    }

    public Car() {

    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Car{" +
                "seats=" + seats +
                '}';
    }
}
