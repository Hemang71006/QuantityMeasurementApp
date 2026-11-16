# Quantity Measurement App

A Java-based Quantity Measurement Application developed as part of the BridgeLabz Java Training program. The project evolves through 17 use cases, progressing from basic measurement equality to a generic, extensible quantity framework and finally a Spring Boot REST API with database persistence.

The application demonstrates Object-Oriented Programming, Generics, Enums, SOLID principles, DRY, design patterns, exception handling, unit conversion, arithmetic operations, file-based persistence, JDBC, Spring Boot, REST APIs, and Spring Data JPA.

---

## Project Information

| Detail | Description |
|---|---|
| Project Name | Quantity Measurement App |
| Author | Hemang |
| Training | BridgeLabz |
| Language | Java 21 |
| Build Tool | Maven |
| Testing Framework | JUnit 5 |
| IDE | IntelliJ IDEA |
| Backend Framework | Spring Boot 3.5.7 |
| Persistence | JDBC and Spring Data JPA |
| API Documentation | OpenAPI / Swagger UI |
| Current Use Cases | UC1–UC17 |

## Project Objectives

- Implement measurement equality across different units.
- Build reusable and generic quantity models.
- Support multiple measurement categories.
- Perform unit conversion and arithmetic operations.
- Maintain precision and validate measurement inputs.
- Follow SOLID, DRY, and object-oriented design principles.
- Separate application responsibilities using layered architecture.
- Persist measurement history.
- Expose measurement operations through RESTful APIs.
- Maintain backward compatibility as the application evolves.

---

# Use Case Progression

## UC1 – Feet Measurement Equality

Introduces basic measurement equality using the Feet measurement.

**Features:**
- Create a Feet measurement representation.
- Compare two Feet measurements based on their values.
- Implement `equals()` and `hashCode()`.
- Handle null values and comparisons with different object types.
- Ensure consistent equality behavior.

**Concepts:** Classes, objects, encapsulation, object equality, and hash codes.

## UC2 – Feet and Inches Measurement Equality

Extends measurement equality to support Feet and Inches.

**Features:**
- Introduce separate Feet and Inches measurement representations.
- Compare Feet with Feet.
- Compare Inches with Inches.
- Compare Feet with Inches through conversion.
- Support the conversion relationship:

`1 Foot = 12 Inches`

- Handle null and incompatible measurement comparisons.

**Concepts:** Unit conversion, reusable methods, equality, and type checking.

## UC3 – Generic Quantity Length (DRY)

Refactors the length measurement implementation to avoid duplicated equality and conversion logic.

**Features:**
- Introduce `LengthUnit`.
- Introduce reusable `QuantityLength`.
- Support FEET and INCHES.
- Normalize values into a common base unit for comparison.
- Preserve compatibility with earlier Feet and Inches functionality.
- Validate null units and non-finite values.

**Concepts:** DRY, enums, abstraction, normalization, and reusable logic.

## UC4 – Extended Length Unit Support

Expands the length measurement category with additional units.

**Supported units:**
- FEET
- INCHES
- YARDS
- CENTIMETERS

**Features:**
- Add Yards and Centimeters.
- Support equality across supported length units.
- Extend conversion logic.
- Validate unsupported units and invalid measurement values.

**Conversion relationships:**
- 1 Yard = 3 Feet
- 1 Centimeter ≈ 0.393701 Inches

**Concepts:** Extensibility, conversion factors, and unit normalization.

## UC5 – Unit Conversion Operation

Introduces explicit conversion as a reusable operation.

**Features:**
- Convert a measurement from one supported length unit to another.
- Support FEET, INCHES, YARDS, and CENTIMETERS.
- Validate source and target units.
- Reject invalid or non-finite values.
- Preserve measurement accuracy.

**Concepts:** Conversion algorithms, validation, and reusable operations.

## UC6 – Addition of Measurements

Introduces addition between compatible length measurements.

**Features:**
- Add measurements expressed in different length units.
- Normalize measurements before addition.
- Return the result in the first operand's unit.
- Preserve measurement immutability.
- Validate measurement compatibility and values.

**Concepts:** Arithmetic operations, base-unit calculations, and immutability.

## UC7 – Addition with Target Unit Specification

Extends addition to allow the caller to specify the result unit.

**Features:**
- Add two compatible length measurements.
- Convert both operands into a common base unit.
- Return the result in a caller-selected target unit.
- Support FEET, INCHES, YARDS, and CENTIMETERS.
- Handle zero and negative values.

**Example:**

`1 FOOT + 12 INCHES = 2 FEET`

The same result can be requested in another supported unit.

**Concepts:** Method overloading, conversion, and flexible result representation.

## UC8 – Standalone Length Unit Enum

Refactors unit-related responsibilities into a standalone enum.

**Features:**
- Introduce a dedicated `LengthUnit` enum.
- Keep unit definitions and conversion responsibilities together.
- Reduce duplicated conversion logic.
- Improve maintainability and extensibility.
- Preserve previous length measurement behavior.

**Concepts:** Enums, encapsulation, separation of responsibilities, and clean code.

## UC9 – Weight Measurement

Introduces a new measurement category: Weight.

**Features:**
- Introduce weight measurement support.
- Implement weight equality.
- Support weight unit conversion.
- Support addition of compatible weight measurements.
- Keep weight operations separate from length operations.

**Concepts:** Generic design preparation, category separation, and reusable measurement logic.

## UC10 – Generic Quantity Class with Unit Interface for Multi-Category Support

Introduces a generic measurement architecture.

**Features:**
- Introduce `IMeasurable` as a common unit interface.
- Introduce `Quantity<U extends IMeasurable>`.
- Refactor LengthUnit and WeightUnit to implement the interface.
- Support generic equality, conversion, and addition.
- Maintain type safety between measurement categories.
- Preserve compatibility with earlier quantity implementations.

**Concepts:** Java Generics, bounded type parameters, interfaces, abstraction, and SOLID principles.

## UC11 – Volume Measurement

Introduces Volume as another measurement category.

**Supported units:**
- LITRE
- MILLILITRE
- GALLON

**Conversion relationships:**
- 1 Litre = 1000 Millilitres
- 1 Gallon = 3.78541 Litres

**Features:**
- Add `VolumeUnit`.
- Reuse the generic `Quantity<U>` implementation.
- Support equality, conversion, and addition.
- Maintain category isolation.
- Validate invalid values and preserve conversion precision.

**Concepts:** Extensible generics, unit conversion, precision, and reusable architecture.

## UC12 – Subtraction and Division Operations on Quantity Measurements

Extends generic arithmetic operations to Length, Weight, and Volume.

**Features:**
- Implement subtraction between compatible quantities.
- Support subtraction with an explicitly specified target unit.
- Implement division between compatible quantities.
- Return a dimensionless ratio for division.
- Perform arithmetic using normalized base-unit values.
- Reject null operands, incompatible categories, invalid values, and division by zero.
- Preserve immutability and full precision.

**Concepts:** Generic arithmetic, method overloading, validation, and exception handling.

## UC13 – Centralized Arithmetic Logic to Enforce DRY in Quantity Operations

Refactors arithmetic implementation to centralize common logic.

**Features:**
- Introduce a private `ArithmeticOperation` enum.
- Centralize operand validation.
- Centralize base-unit arithmetic.
- Use reusable arithmetic helpers.
- Preserve public addition, subtraction, and division APIs.
- Maintain existing behavior and test compatibility.

**Concepts:** DRY, lambda expressions, `DoubleBinaryOperator`, code reuse, and maintainability.

## UC14 – Temperature Measurement with Selective Arithmetic Support and IMeasurable Refactoring

Introduces Temperature as a measurement category with offset-based conversions.

**Supported units:**
- CELSIUS
- FAHRENHEIT
- KELVIN

**Features:**
- Introduce `TemperatureUnit`.
- Support temperature equality and conversion.
- Implement offset-based conversion formulas.
- Refactor `IMeasurable` with default capability methods.
- Introduce `SupportsArithmetic` to represent arithmetic capability.
- Allow arithmetic for supported measurement categories.
- Reject unsupported temperature arithmetic with `UnsupportedOperationException`.

**Concepts:** Interface default methods, capability-based design, polymorphism, and selective operation support.

## UC15 – N-Tier Architecture Refactoring for Quantity Measurement Application

Refactors the application into independent architectural layers.

**Layers:**
1. Application
2. Controller
3. Service
4. Repository
5. Entity / Model
6. DTO

**Features:**
- Introduce `QuantityDTO` and `QuantityModel`.
- Introduce serializable `QuantityMeasurementEntity`.
- Define repository interfaces and a cache-based implementation.
- Persist measurement history using serialized file storage.
- Introduce service interfaces and implementations.
- Move business operations into the service layer.
- Make the controller delegate operations to the service.
- Support comparison, conversion, addition, subtraction, division, and history retrieval.
- Introduce dependency injection and centralized exception handling.

**Design patterns and principles:**
- Factory
- Facade
- Singleton
- Dependency Injection
- SOLID
- Separation of Concerns

**Concepts:** N-tier architecture, DTOs, repository pattern, service layer, and testability.

## UC16 – Database Integration with JDBC for Quantity Measurement Persistence

Introduces relational database persistence using JDBC.

**Features:**
- Integrate H2 database.
- Use JDBC for database operations.
- Configure HikariCP connection pooling.
- Introduce database configuration and connection management.
- Initialize the database schema.
- Implement `QuantityMeasurementDatabaseRepository`.
- Persist measurement history in a relational database.
- Support history retrieval, filtering, counting, and deletion.
- Integrate database persistence with the repository and service layers.
- Support configurable repository selection.
- Add logging and database exception handling.
- Preserve compatibility with UC1–UC15.

**Technologies:**
- JDBC
- H2 Database
- HikariCP
- SLF4J / Logback

**Concepts:** Database connectivity, connection pooling, repository abstraction, SQL, and persistence.

## UC17 – Spring Framework Integration: REST Services and JPA for Quantity Measurement

Transforms the application into a Spring Boot REST service while preserving previous measurement functionality.

**Features:**
- Introduce Spring Boot application configuration.
- Integrate Spring Web and REST controllers.
- Integrate Spring Data JPA.
- Map measurement history to JPA entities.
- Implement a JPA repository.
- Adapt the existing repository abstraction to JPA.
- Integrate existing business logic through dependency injection.
- Introduce request and response DTOs.
- Add request validation.
- Implement centralized REST exception handling.
- Configure Spring Security.
- Expose API documentation using OpenAPI and Swagger UI.
- Enable Spring Boot Actuator health and metrics endpoints.
- Use H2 for development persistence.
- Preserve UC16 database configuration and existing functionality.

**REST API base path:**

`/api/v1/quantities`

**Technologies:**
- Spring Boot 3.5.7
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Boot Validation
- Spring Boot Actuator
- H2 Database
- Springdoc OpenAPI
- Maven
- JUnit 5
- MockMvc

---

# Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Core programming language |
| Maven | Build and dependency management |
| JUnit 5 | Unit and integration testing |
| Spring Boot 3.5.7 | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | ORM and persistence |
| JDBC | Database connectivity |
| H2 | Database |
| HikariCP | Connection pooling |
| Spring Security | API security configuration |
| Spring Validation | Request validation |
| Spring Boot Actuator | Application health and monitoring |
| Springdoc OpenAPI | API documentation |
| Git and GitHub | Version control |

# Architecture

The application evolves from a simple measurement implementation into a layered backend application.

```text
QuantityMeasurementApp
        |
        v
   Controller Layer
        |
        v
    Service Layer
        |
        v
  Repository Layer
        |
        v
  Persistence Layer
        |
        v
     H2 Database
```

Supporting components include:

- **Model:** Measurement and quantity domain objects.
- **DTO:** Request and response data transfer objects.
- **Entity:** Database-mapped measurement history.
- **Repository:** Data access abstraction.
- **Service:** Measurement business logic.
- **Controller:** REST endpoint handling.
- **Configuration:** Spring Boot, security, and OpenAPI configuration.
- **Exception:** Centralized exception handling.

# REST API Reference

Base URL:

`http://localhost:8080/api/v1/quantities`

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/compare` | Compare two quantities |
| POST | `/convert` | Convert a quantity to another unit |
| POST | `/add` | Add two quantities |
| POST | `/subtract` | Subtract two quantities |
| POST | `/multiply` | Multiply quantity values |
| POST | `/divide` | Divide two quantities |
| GET | `/history` | Retrieve measurement history |
| GET | `/history/operation/{operation}` | Filter history by operation |
| GET | `/history/type/{measurementType}` | Filter history by measurement type |
| GET | `/history/errors` | Retrieve failed measurement records |
| GET | `/history/count` | Retrieve the number of stored records |

## API Documentation and Monitoring

| Resource | URL |
|---|---|
| Swagger UI | `http://localhost:8080/swagger-ui.html` |
| OpenAPI JSON | `http://localhost:8080/api-docs` |
| H2 Console | `http://localhost:8080/h2-console` |
| Health Check | `http://localhost:8080/actuator/health` |
| Metrics | `http://localhost:8080/actuator/metrics` |
| Application Info | `http://localhost:8080/actuator/info` |

# Getting Started

## Prerequisites

Install the following:

- Java Development Kit (JDK) 21
- Apache Maven
- IntelliJ IDEA or another Java IDE
- Git

Verify the installations:

```bash
java -version
mvn -version
git --version
```

## Clone the Repository

```bash
git clone https://github.com/Hemang71006/QuantityMeasurementApp.git
cd QuantityMeasurementApp
```

## Build the Project

```bash
mvn clean compile
```

## Run Tests

```bash
mvn clean test
```

## Run the Spring Boot Application

```bash
mvn spring-boot:run
```

Alternatively, run `QuantityMeasurementApplication.java` from IntelliJ IDEA.

The application starts on:

`http://localhost:8080`

# Testing

The project follows Test-Driven Development (TDD), with JUnit 5 used to validate functionality throughout the use cases.

Testing includes:

- Measurement equality.
- Unit conversion.
- Cross-unit comparisons.
- Addition and subtraction.
- Division and invalid divisors.
- Generic type and measurement-category validation.
- Temperature conversion.
- Precision and finite-value validation.
- Immutability.
- Repository and persistence behavior.
- REST controller and request validation.
- Application context loading.
- API history and count operations.

**Latest UC17 verification:** `mvn clean test` completed successfully with 360 tests, 0 failures, 0 errors, and 0 skipped tests.

# Git Workflow

The project uses Git feature branches to implement individual use cases.

Main branches:

- `main` – Main project documentation and stable project branch.
- `dev` – Integrated development branch.
- `feature/*` – Individual use-case implementation branches.

Feature branches are merged into `dev` after implementation and testing.

Commit message convention:

```text
[Hemang]: Description of the change
```

# Learning Outcomes

Through UC1–UC17, this project demonstrates practical implementation of:

- Core Java and Object-Oriented Programming.
- Encapsulation, inheritance, abstraction, and polymorphism.
- `equals()` and `hashCode()`.
- Enums and interfaces.
- Java Generics and bounded type parameters.
- Unit conversion and arithmetic algorithms.
- Exception handling and input validation.
- SOLID and DRY principles.
- Lambda expressions and functional interfaces.
- Immutable domain models.
- N-tier architecture.
- DTO, Entity, Repository, Service, and Controller patterns.
- Factory, Facade, and Singleton design patterns.
- File-based and relational database persistence.
- JDBC and connection pooling.
- Spring Boot application development.
- REST API design and implementation.
- Spring Data JPA.
- API validation, security, documentation, and monitoring.
- Unit and integration testing with JUnit 5 and MockMvc.

---

## Author

**Hemang**

GitHub: [Hemang71006](https://github.com/Hemang71006)

Repository: [QuantityMeasurementApp](https://github.com/Hemang71006/QuantityMeasurementApp)

**BridgeLabz Java Training – Quantity Measurement Application (UC1–UC17)**