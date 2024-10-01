package com.ironhack.week7tuesday.model;

import jakarta.persistence.Embeddable;

// envevidible, insertable
@Embeddable
public class Address {
    private String street;
    private String city;
    private String postalCode;

    // constructor , getters, setters
}
