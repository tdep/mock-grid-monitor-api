# Mock Grid Monitor API
### Overview

This demo API is a simple Spring Boot RESTful service that is used to manage data for imaginary power stations. It supports full CRUD operations, input validation, and error handling. Built using Hibernate/JPA, H2, Mockito, and Maven.

===
### Features
- Create, Read, Update, Delete power station records
- Input validation with detail error responses
- Global exception handling with related HTTP status codes and custom messaging
- Unit and integration tests using JUnit and Mockito
- Built using Java, Spring Boot, Hibernate/JPA, and H2 (in-memory DB for tests)
===
### Technologies
- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- Hibernate
- H2 Database (for testing)
- Maven
- JUnit 5, Mockito (for testing)
- Lombok
- MockMvc (for integration testing)
===
### Getting Started
#### Prrerequisites
- Java 17+ installed
- Maven installed
#### Running the App
1. Clone the repo
2. Navigate to project directory
3. Build and run: `mvn spring-boot:run`
4. The API will be available at `http://localhost:8080/api/powerstations`
===
### API Endpoints

| Method | Endpoint                  | Description                |
|--------|---------------------------|----------------------------|
| GET    | `/api/powerstations`      | List all power stations    |
| GET    | `/api/powerstations/{id}` | Get a single station by ID |
| POST   | `/api/powerstations`      | Create a new power station |
| PUT    | `/api/powerstations/{id}` | Update an existing station |
| DELETE | `/api/powerstations/{id}` | Delete a power station     |

===
### Power Station Model

| Field        | Type     | Description                        | Validation                   |
|--------------|----------|------------------------------------|------------------------------|
| `id`         | `Long`   | Unique identifier (auto-generated) | *None*                       |
| `name`       | `String` | Name of the power station          | Required, max 100 characters |
| `capacityMw` | `Double` | Energy capacity in megawatts (MW)  | Required, must be positive   |
| `region`     | `String` | Physical location of the station   | Required                     |
| `status`     | `String` | Whether it's active or innactive   | *None*                       |

#### Example JSON
```
{
    "id": 1,
    "name": "The Hamster Wheel",
    "capacityMw": 0000.1,
    "region": "Eastern New York",
    "status": "Active"
}
```

### Example Requests (cURL)

#### Create a Power Station
```
curl -X POST http://localhost:8080/api/powerstations \
-H "Content-Type: application/json" \
-d '{
    "name": "Magic Power Plant",
    "capacityMw": 1000.5,
    "region": "Central Maine",
    "status": "Active"
}'
```

#### Get All Power Stations
```
curl http://localhost:8080/api/powerstations
```

#### Get a Single Power Station by ID
```
curl http://localhost:8080/api/powerstations/1
```

#### Update a Power Station
```
curl -X PUT http://localhost:8080/api/powerstations/1 \
-H "Content-Type: application/json" \
-d '{
    "name": "Magic Power Plant - Updated",
    "capacityMw": 1001.5,
    "region": "Central Main",
    "status": "Active"
}'
```

#### Delete a Power Station
```
curl -X DELETE http://localhost:8080/api/powerstations/1
```

===
### Validation & Error Handling
- Validates incoming data with annotations such as `@NotBlank` and `@Positive`
- Returns `HTTP 400` with detailed messages for validation failures
- Returns `HTTP 404` if a resource is not found
- Returns `HTTP 500` for unexpected errors

===
### Running Tests
Run all tests with: `mvn test`

===
### Notes and Future Work
- Currently uses an in-memory H2 database for testing - can be configured for persistent DB
- Authentication and security are not included by could be added
- Generated API documentation can also be added
- More rovust validations can be added