package com.ironhack.week7tuesday.exercise;

import com.ironhack.week7tuesday.exercise.model.AddressExercise;
import com.ironhack.week7tuesday.exercise.model.Employee;
import com.ironhack.week7tuesday.exercise.model.Engineer;
import com.ironhack.week7tuesday.exercise.model.Manager;
import com.ironhack.week7tuesday.exercise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeCommandLineRunner implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------------------------------EXERCISE------------------------------------");

        AddressExercise address = new AddressExercise("elm street", "New York", "1234", "Sesamo");
        Employee employee = new Employee("Pepito", 1200, address);

        Employee savedEmployee = employeeRepository.save(employee);
        System.out.println("Saved employee " + savedEmployee);

        // Guardar un Manager
        Manager manager = new Manager("John Doe", 80000, address, 3);
        Manager savedManager = employeeRepository.save(manager);
        System.out.println("Saved manager " + savedManager);

        // Guardar un Engineer
        Engineer engineer = new Engineer("Jane Smith", 70000, address, "Software Development");
        Engineer savedEngineer = employeeRepository.save(engineer);
        System.out.println("Saved engineer " + savedEngineer);

        // Recuperar todos los empleados
        System.out.println("All employees:");
        employeeRepository.findAll().forEach(System.out::println);

        // Buscar empleado por ID
        Long id = engineer.getId();
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        foundEmployee.ifPresent(e -> System.out.println("Found employee: " + e.getName()));

        // Eliminar empleado por ID
        employeeRepository.deleteById(id);
        System.out.println("Employee with ID " + id + " deleted");

        // Mostrar todos los empleados restantes
        System.out.println("Remaining employees:");
        employeeRepository.findAll().forEach(System.out::println);
    }
}
