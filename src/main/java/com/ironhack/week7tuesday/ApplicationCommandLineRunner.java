package com.ironhack.week7tuesday;


import com.ironhack.week7tuesday.model.Bike;
import com.ironhack.week7tuesday.model.Car;
import com.ironhack.week7tuesday.model.Vehicle;
import com.ironhack.week7tuesday.repository.BikeRepository;
import com.ironhack.week7tuesday.repository.CarRepository;
import com.ironhack.week7tuesday.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Override
    public void run(String... args) throws Exception {

        // Guardar un coche ,una bici y un vehículo
        Car car = new Car("Mercedes", 2);
        System.out.println("CAR ID " + car.getId());
        Car carFromDB = carRepository.save(car);
        System.out.println("CAR ID " + carFromDB.getId());

        Bike bike = new Bike("veranoAzul", true);
        bikeRepository.save(bike);

        Vehicle vehicle = new Vehicle("janeDoe");
        vehicleRepository.save(vehicle);
        //vehicleRepository.save(new Vehicle("janeDoe"));

        // Mostrar todos los vehiculos
        List<Vehicle> vehicleList = vehicleRepository.findAll();

        for (Vehicle vehicleFromList : vehicleList) {
            if (vehicleFromList instanceof Car car1) {
                System.out.println("The car :" + car1);
            } else if (vehicleFromList instanceof Bike bike1) {
                System.out.println("The bike :" + bike1);
            } else {
                System.out.println("The vehicle " + vehicleFromList);
            }
        }

        for (Car car1 : carRepository.findAll()) {
            System.out.println("From the carRepository " + car1);
        }

        // buscar un coche por su ID

        Long carId = carFromDB.getId();
        // Long carId = 0L;
        Optional<Car> foundCar = carRepository.findById(carId);

        if (foundCar.isPresent()) {
            System.out.println("Found Car with ID " + carId);
        } else if (foundCar.isEmpty()) {
            System.out.println("No Car found with ID " + carId);
        }


//        Car secondFoundCar = carRepository.findCarById(carId);
//        System.out.println("Found Car with ID" + secondFoundCar.getId());


        // eliminar coche por ID

        // findById, existsById
        carRepository.deleteById(carId);
        System.out.println("Deleted car with ID " + carId);
    }
}
