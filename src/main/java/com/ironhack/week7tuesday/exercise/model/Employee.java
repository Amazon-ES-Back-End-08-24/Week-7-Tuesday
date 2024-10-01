package com.ironhack.week7tuesday.exercise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "employee_type")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {
    @Id
    //@GeneratedValue(strategy = GenerationType.TABLE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double salary;

    @Embedded
    private AddressExercise address;

    public Employee(Long id, String name, double salary, AddressExercise address) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.address = address;
    }

    public Employee() {

    }

    public Employee(String name, double salary, AddressExercise address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public AddressExercise getAddress() {
        return address;
    }

    public void setAddress(AddressExercise address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", address=" + address +
                '}';
    }
}
