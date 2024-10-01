package com.ironhack.week7tuesday.model;

import jakarta.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_name")
    private String name;

    @Embedded // envevida, insertada
    private Address primaryAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "secondary_street")),
            @AttributeOverride(name = "city", column = @Column(name = "secondary_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "secondary_postal_ code"))
    })
    @Embedded
    private Address secondaryAddress;
}
