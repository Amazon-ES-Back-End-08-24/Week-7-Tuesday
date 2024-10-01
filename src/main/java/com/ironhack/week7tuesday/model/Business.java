package com.ironhack.week7tuesday.model;

import jakarta.persistence.*;

@Entity
@Table(name = "business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logo;

    @Embedded // embebida, insertada
    private Address address;
}
