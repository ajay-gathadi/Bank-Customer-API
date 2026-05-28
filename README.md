# Bank Customer API

A RESTful CRUD API for managing bank customers, built with **Spring Boot 4** and **H2 Database**.

---

## Tech Stack

| Technology      | Version                 |
| --------------- | ----------------------- |
| Java            | 21                      |
| Spring Boot     | 4.0.5                   |
| Spring Data JPA | via Spring Boot Starter |
| Hibernate       | 7.x                     |
| H2 Database     | File-based              |
| Maven           | Wrapper included        |

---

## Project Structure

```
src/main/java/com/bank/app/
├── BankCustomerApiApplication.java        # Entry point
├── controller/
│   └── CustomerController.java            # REST endpoints
├── model/
│   └── Customer.java                      # JPA Entity
├── repository/
│   └── CustomerRepository.java            # Data access layer
└── service/
    ├── CustomerService.java               # Service interface
    └── CustomerServiceImplementation.java # Business logic
```

---

## Customer Entity

| Field         | Type   | Constraint                  |
| ------------- | ------ | --------------------------- |
| `customerId`  | Long   | Primary Key, Auto-generated |
| `firstName`   | String |                             |
| `lastName`    | String |                             |
| `email`       | String | Unique                      |
| `phoneNumber` | String |                             |

---

## API Endpoints

Base URL: `http://localhost:8080`

### Create a Customer

```
POST /api/customers
```

**Request Body:**

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890"
}
```

**Responses:**

| Status         | Description                                  |
| -------------- | -------------------------------------------- |
| `201 Created`  | Customer created successfully                |
| `409 Conflict` | Customer with the given email already exists |

---

### Get All Customers

```
GET /api/customers
```

**Responses:**

| Status           | Description                   |
| ---------------- | ----------------------------- |
| `200 OK`         | Returns list of all customers |
| `204 No Content` | No customers exist yet        |

---

### Get Customer by ID

```
GET /api/customers/{customerId}
```

**Responses:**

| Status          | Description                          |
| --------------- | ------------------------------------ |
| `200 OK`        | Returns the customer object          |
| `404 Not Found` | Customer not found with the given ID |

**Example Response (200):**

```json
{
  "customerId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "1234567890",
  "email": "john.doe@example.com"
}
```

---

### Update a Customer

```
PUT /api/customers/{customerId}
```

**Request Body:**

```json
{
  "firstName": "Jane",
  "lastName": "Smith"
}
```

**Responses:**

| Status          | Description                          |
| --------------- | ------------------------------------ |
| `200 OK`        | Returns the updated customer object  |
| `404 Not Found` | Customer not found with the given ID |

> **Note:** Currently updates `firstName` and `lastName` only.

---

### Delete a Customer

```
DELETE /api/customers/{customerId}
```

**Responses:**

| Status          | Description                          |
| --------------- | ------------------------------------ |
| `200 OK`        | Returns the deleted customer object  |
| `404 Not Found` | Customer not found with the given ID |

---

## Getting Started

### Prerequisites

- Java 21
- Maven (or use the included Maven wrapper)

### Run the Application

```bash
./mvnw spring-boot:run
```

### Test with Postman

Import the endpoints listed above into Postman or any REST client and start making requests.

---

## Database Configuration

The application uses an **H2 file-based database** that persists data to `customerdb.mv.db` in the project root.

| Property | Value                                            |
| -------- | ------------------------------------------------ |
| JDBC URL | `jdbc:h2:file:./customerdb;AUTO_SERVER=TRUE`     |
| Username | `sa`                                             |
| Password | _(empty)_                                        |
| DDL Auto | `create-drop` (tables recreated on each restart) |

> **Tip:** Change `spring.jpa.hibernate.ddl-auto` to `update` in `application.properties` to preserve data across application restarts.

---

## Error Handling

The API uses `ResponseStatusException` in the Service layer to throw meaningful errors:

| Scenario                | Status Code     | Message                                     |
| ----------------------- | --------------- | ------------------------------------------- |
| Duplicate email on POST | `409 Conflict`  | Customer with email: {email} already exists |
| Customer not found      | `404 Not Found` | Customer Not Found                          |
