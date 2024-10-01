# Week 7
# Tabla de Contenidos

1. [Herencia en JPA](#herencia-en-jpa)
    - [Single Table](#single-table---por-defecto)
    - [Table Per Class](#table-per-class)
    - [Joined](#joined)
2. [Clases Embebibles](#clases-embebibles)
3. [Inyección de Dependencias con @Autowired](#inyección-de-dependencias-con-autowired)
4. [CommandLineRunner para Pruebas CRUD](#commandlinerunner-para-pruebas-crud)
5. [Ejercicio Práctico: Herencia en JPA con `CommandLineRunner`](#ejercicio-práctico-herencia-en-jpa-con-commandlinerunner)
---

## Herencia en JPA

### Single Table - POR DEFECTO

En este enfoque, todas las clases de una jerarquía de herencia se mapean a una sola tabla en la base de datos. Se utiliza una columna discriminadora que identifica el tipo de la entidad.
![Screenshot 2024-10-01 at 17.59.46.png](src/main/resources/static/Screenshot%202024-10-01%20at%2017.59.46.png)


```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Campos comunes
}

@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicle {
    private int seats;
    // Campos específicos
}

@Entity
@DiscriminatorValue("BIKE")
public class Bike extends Vehicle {
    private boolean hasBasket;
    // Campos específicos
}
```

### Table Per Class

- Cada subclase tiene su propia tabla, lo que mejora la normalización, pero hace las consultas más complejas.

- Dado que estas tablas no están vinculadas por una estructura común (como lo estarían en la estrategia JOINED, donde una columna de clave primaria única podría ser compartida entre todas las tablas), se hace necesario asegurar que los identificadores utilizados en estas tablas sean únicos. La estrategia TABLE facilita esto al mantener un contador global (o contadores) para los ID que se pueden compartir entre todas las subclases, asegurando identificadores únicos en las diferentes tablas de entidades.

![Screenshot 2024-10-01 at 17.55.00.png](src/main/resources/static/Screenshot%202024-10-01%20at%2017.55.00.png)
```java
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    // Campos comunes
}

@Entity
public class Car extends Vehicle {
    private int seats;
    // Campos específicos
}

@Entity
public class Bike extends Vehicle {
    private boolean hasBasket;
    // Campos específicos
}
```

### Joined

Cada clase tiene su propia tabla, pero las tablas están unidas mediante claves foráneas. Esto mejora el almacenamiento eficiente de datos.
![Screenshot 2024-10-01 at 17.58.08.png](src/main/resources/static/Screenshot%202024-10-01%20at%2017.58.08.png)

```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Campos comunes
}

@Entity
public class Car extends Vehicle {
    private int seats;
    // Campos específicos
}

@Entity
public class Bike extends Vehicle {
    private boolean hasBasket;
    // Campos específicos
}
```

---

## Clases Embebibles

Las clases embebibles permiten incluir atributos comunes en múltiples entidades sin necesidad de crear relaciones entre tablas.

```java
@Embeddable
public class Address {
    private String street;
    private String city;
    private String postalCode;
}

@Entity
public class Person {
    @Id
    private Long id;

    @Embedded
    private Address address;

    @AttributeOverrides({
            @AttributeOverride(name="street",column=@Column(name="second_street")),
            @AttributeOverride(name="city",column=@Column(name="second_city")),
            @AttributeOverride(name="postalCode",column=@Column(name="second_postal"))
    })
    private Address address;
}
```

---

## Inyección de Dependencias con @Autowired

- **Definición**: Un bean en Spring es un objeto que es administrado por el contenedor de Spring. En otras palabras, es una instancia de una clase que es creada, configurada y gestionada por el framework Spring.

- **`@Autowired`**: Esta anotación se utiliza para la inyección automática de dependencias. Esto significa que Spring se encarga de crear instancias de las clases y suministrarlas (o "inyectarlas") donde sean necesarias, en lugar de que el desarrollador lo haga manualmente.

- **Inyección de dependencias**: Este patrón de diseño permite desacoplar las clases de sus dependencias, mejorando la modularidad y facilitando la gestión de las dependencias.

```java
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
```

---

## CommandLineRunner para Pruebas CRUD

Usar un `CommandLineRunner` te permite ejecutar código cuando la aplicación inicia, facilitando la prueba de las operaciones CRUD como `findAll`, `save`, `delete`, y `findById`.

```java
@Component
public class VehicleCommandLineRunner implements CommandLineRunner {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Guardar un coche y una bicicleta
        Car car = new Car();
        car.setSeats(4);
        vehicleRepository.save(car);

        Bike bike = new Bike();
        bike.setHasBasket(true);
        vehicleRepository.save(bike);

        // Mostrar todos los vehículos
        for (Vehicle vehicle : vehicleRepository.findAll()){
            System.out.println(vehicle);
            
            if (vehicle instanceof Car) {
                System.out.println("Car : " + ((Car) vehicle).getSeats());
            } else if (vehicle instanceof Bike) {
                System.out.println("Bike : " + ((Bike) vehicle).isHasBasket());
            }
        }

        // Buscar vehículo por ID
        Long id = car.getId(); // Supongamos que queremos buscar el coche
        Optional<Vehicle> foundVehicle = vehicleRepository.findById(id);
        if (foundVehicle.isPresent()) {
            System.out.println("Found vehicle with ID " + id + ": " + foundVehicle.get().getClass().getSimpleName());
        } else {
            System.out.println("Vehicle with ID " + id + " not found");
        }

        // Eliminar un vehículo
        vehicleRepository.deleteById(id);
        System.out.println("Vehicle with ID " + id + " deleted");

    }
}
```
---

## Optional

- **Definición**: `Optional` es una clase en Java que ofrece una forma más segura de manejar valores que pueden ser nulos. Permite verificar la presencia de un valor antes de acceder a él y proporciona métodos para obtener el valor si está presente, o para establecer un valor por defecto si no lo está.

- **Uso**: En lugar de devolver directamente un valor que podría ser `null`, se puede devolver un `Optional` para evitar errores de puntero nulo (NullPointerException).

```java
Optional<Book> bookOptional = bookRepository.findById(1);
if (bookOptional.isPresent()) {
    Book book = bookOptional.get();
} else {
    // Manejar la ausencia del libro
}
```

---

### Ejercicio Práctico: Herencia en JPA con `CommandLineRunner`

**Objetivo**: Practicar el uso de herencia en JPA con las estrategias `SINGLE_TABLE`, `TABLE_PER_CLASS`, `JOINED` y embeddable. Además, implementar un `CommandLineRunner` para realizar operaciones básicas CRUD (`save()`, `findAll()`, `findById()`, `deleteById()`).

---

#### Requisitos

1. **Herencia**:
    - Crea una jerarquía de clases que representen empleados.
        - `Employee` como clase base.
        - `Manager` y `Engineer` como subclases.
    - Implementa la estrategia de herencia `SINGLE_TABLE`.
    - Luego, cambia la estrategia a `TABLE_PER_CLASS` y después a `JOINED` para observar cómo afecta la estructura de la base de datos.

2. **Embeddable**:
    - Crea una clase `Address` que contenga los atributos de dirección (calle, ciudad, código postal).
    - Inserta esta clase como un objeto embebido dentro de la clase `Employee`.

3. **CommandLineRunner**:
    - Implementa un `CommandLineRunner` para probar las siguientes operaciones:
        - **Guardar (`save()`)**: Crea instancias de `Manager` y `Engineer` con direcciones embebidas y guárdalas en la base de datos.
        - **Buscar todos (`findAll()`)**: Recupera y muestra todos los empleados.
        - **Buscar por ID (`findById()`)**: Busca un empleado por su ID y muestra su información.
        - **Eliminar por ID (`deleteById()`)**: Elimina un empleado de la base de datos usando su ID.
        - **Mostrar los empleados restantes**: Después de eliminar, muestra todos los empleados que quedan en la base de datos.

---

