package com.ironhack.week7tuesday.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@DiscriminatorValue("BIKE")
@Table(name = "bikes") // <-- NO con SINGLE_TABLE
public class Bike extends Vehicle {

    private boolean hasBasket;

    public Bike(Long id, String brand, boolean hasBasket) {
        super(id, brand);
        this.hasBasket = hasBasket;
    }

    public Bike(String brand, boolean hasBasket) {
        super(brand);
        this.hasBasket = hasBasket;
    }

    public Bike(boolean hasBasket) {
        this.hasBasket = hasBasket;
    }

    public Bike() {

    }

    public boolean isHasBasket() {
        return hasBasket;
    }

    public void setHasBasket(boolean hasBasket) {
        this.hasBasket = hasBasket;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "hasBasket=" + hasBasket +
                '}';
    }
}
