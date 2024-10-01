package com.ironhack.week7tuesday.repository;

import com.ironhack.week7tuesday.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findCarById(Long carId);
}
