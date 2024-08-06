# Demo User Application with MongoDB

This project is a Spring Boot application that demonstrates user management with MongoDB integration. It provides RESTful API endpoints for managing users and their associated tasks.

## Features

- Create, read, update, and delete users
- Add, update, and delete tasks for users
- Calculate average task score for a user
- MongoDB integration for data persistence

## Prerequisites

- Java 17
- Maven
- MongoDB
  
## Technologies Used

- Spring Boot
- Spring Data MongoDB
- Lombok
- Maven

The application will start running at `http://localhost:8092`.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users` | Create a new user |
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get a user by ID |
| PUT | `/api/users/{id}` | Update a user |
| DELETE | `/api/users/{id}` | Delete a user |
| POST | `/api/users/with-tasks` | Create a user with tasks |
| POST | `/api/users/{userId}/tasks` | Add a task to a user |
| GET | `/api/users/{userId}/tasks` | Get all tasks for a user |
| PUT | `/api/users/{userId}/tasks/{taskIndex}` | Update a task for a user |
| DELETE | `/api/users/{userId}/tasks/{taskIndex}` | Delete a task for a user |
| GET | `/api/users/{userId}/average-score` | Get the average task score for a user |

## Configuration

The application is configured to connect to a local MongoDB instance. You can modify the connection settings in the `application.yml` file:

```yaml
spring:
data:
 mongodb:
   host: localhost
   port: 27017
   database: userdb
