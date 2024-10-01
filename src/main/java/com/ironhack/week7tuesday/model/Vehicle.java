package com.ironhack.week7tuesday.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "vehicle_type")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle {
    @Id
    // Solo para TABLE_PER_CLASS
    @GeneratedValue(strategy = GenerationType.TABLE)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    public Vehicle(Long id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                '}';
    }
}
