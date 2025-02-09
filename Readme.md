# Project Documentation

## Project Overview

This project is an Event Registration System that allows users to manage events and their registrations. It is built using Java, Spring Boot, and Maven.

## Technologies Used

- Java
- Spring Boot
- Maven
- JUnit (for testing)
- Mockito (for mocking in tests)

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── thinkify/
│   │           └── event/
│   │               └── registration/
│   │                   └── system/
│   │                       ├── authentication/
│   │                       │   ├── authenticationDTO/
│   │                       │   ├── AuthenticationRequest.java
│   │                       │   ├── AuthenticationResponse.java
│   │                       │   ├── RegistrationRequest.java
│   │                       │   ├── config/
│   │                       │   │    ├── service/
│   │                       │   │    │    └── JWTService.java
│   │                       │   │    ├── ApplicationConfig.java
│   │                       │   │    ├── JWTAuthenticationFilter.java
│   │                       │   │    ├── SecurityConfig.java
│   │                       │   ├── controller/
│   │                       │   │    ├── AuthenticationController.java
│   │                       │   ├── service/
│   │                       │   │    ├── AuthenticationService.java
│   │                       │   ├── user/
│   │                       │   │    ├── Repository/
│   │                       │   │    │    └── UserRepository.java
│   │                       │   │    ├── userDTO/
│   │                       │   │    │    ├── Role.java
│   │                       │   │    │    └── User.java
│   │                       ├── exception/
│   │                       │   └── BadRequest.java
│   │                       ├── manager/
│   │                       │   ├── managerDTO/
│   │                       │   │   ├── EventsEntity.java
│   │                       │   │   └── RegistrationDetails.java
│   │                       │   ├── repository/
│   │                       │   │   ├── EventManagementRepository.java
│   │                       │   │   └── RegistrationDetailsRepository.java
│   │                       │   └── service/
│   │                       │       └── EventManagementService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── thinkify/
                └── event/
                    └── registration/
                        └── system/
                            └── manager/
                                └── service/
                                    └── EventManagementServiceTest.java
```

## Key Components

### Entities

- `EventsEntity`: Represents an event with details such as event name, date, location, and registration details.
- `RegistrationDetails`: Represents the registration details for an event.

### Repositories

- `EventManagementRepository`: Repository interface for managing `EventsEntity` objects.
- `RegistrationDetailsRepository`: Repository interface for managing `RegistrationDetails` objects.

### Services

- `EventManagementService`: Service class that provides methods to manage events, including creating, updating, retrieving, and deleting events.

## Setting Up and Running the Spring Boot Application

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven
- An IDE (e.g., IntelliJ IDEA)

### Setup Instructions

1. **Clone the Repository**

   ```sh
   git clone https://github.com/alanchristy-droid/Event-Management-System.git
   cd <repository-directory>
   ```

2. **Build the Project**

   ```sh
   mvn clean install
   ```

3. **Run the Application**
   ```sh
   mvn spring-boot:run
   ```

### User Registration and Authentication

1. **Register a New User**

   - Send a `POST` request to `/api/auth/register` with the following JSON payload:
     ```json
     {
       "firstName": "new",
       "lastName": "user",
       "email": "new-user@mail.com",
       "password": "password123"
     }
     ```

   ```

   ```

2. **Authenticate a User**
   - Send a `POST` request to `/api/auth/login` with the following JSON payload:
     ```json
     {
       "username": "newuser",
       "password": "password123"
     }
     ```
   - The response will include a JWT token that you will use to access secured APIs.

### Accessing Secured APIs

1. **Include the JWT Token in Requests**

   - For secured endpoints, include the JWT token in the `Authorization` tab in Postman of your requests:
     ```http
     Authorization: Bearer <your-jwt-token>
     ```
   - Under `Authorization` tab in Postman change the Auth Type to Bearer Token and pass token in the token field:

2. **Example of Accessing a Secured API**
   - To access a secured endpoint, such as retrieving events, send a `GET` request to `/api/events` with the `Authorization` header:
     ```http
     GET /api/events
     Authorization: Bearer <your-jwt-token>
     ```

## Testing

The project uses JUnit and Mockito for testing. The test classes are located in the `src/test/java` directory.

### Test Class

- `EventManagementServiceTest`: Contains unit tests for the `EventManagementService` class.

### Running Tests

1. **Navigate to the Project Directory**

   ```sh
   cd <repository-directory>
   ```

2. **Run Tests**
   ```sh
   mvn test
   ```

## Configuration

The application configuration is located in the `application.properties` file in the `src/main/resources` directory.
````
