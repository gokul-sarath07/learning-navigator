# Learning Navigator

## Problem Statement
Develop a RESTful API service using Spring Boot to manage the exam enrollment process for a Learning Management System (LMS). The system will use MySQL for data persistence.

## Problem Description
The exam registration service is a crucial part of a Learning Management System. Generally, the exam registration process requires thorough authentication and authorization. However, this project focuses on building a simplified version of the exam registration service, meeting the following requirements.

## Requirements

The API must handle CRUD operations for **Students**, **Subjects**, and **Exams**. The entities and relationships must be persisted in a MySQL database using foreign key relationships wherever necessary.

### Student Entity
Each student must have the following fields:
- **Student Registration ID** (Unique Identifier)
- **Student Name**
- **List of enrolled Subjects**
- **List of registered Exams**

### Subject Entity
Each subject must have the following fields:
- **Subject ID** (Unique Identifier)
- **Subject Name**
- **List of registered Students**

### Exam Entity
Each exam must have the following fields:
- **Exam ID** (Unique Identifier)
- **Subject**
- **List of enrolled Students**

### Key Functionalities:
- Students can **register for exams** only after enrolling in the corresponding subject.
- Handle common errors gracefully and return appropriate HTTP status codes (e.g., `404 Not Found` for user-related errors).
- Use `@ControllerAdvice` and a `GlobalExceptionHandler` to streamline and organize exception handling.

### Testing:
- Include basic unit tests utilizing **MockMvc** and **Mockito**.
- Minimum of 3 unit tests should be implemented.

## Additional Requirement: Easter Egg Feature

In software development, an "Easter egg" refers to a hidden feature, message, or joke intentionally inserted by developers into the software. In this project, an Easter egg feature will use the **Numbers API** to generate random facts about numbers.

### Easter Egg Feature Details:
- The feature will be triggered by a hidden endpoint that receives a number as a path parameter and returns a random fact about the number.
- You will use the Numbers API to implement this functionality. Refer to the Numbers API documentation to understand its usage.

## Endpoints

### Core API Endpoints:
- **POST /exams/{examId}** - Registers a student for a specific exam

### Easter Egg Feature Endpoint:
- **GET /hidden-feature/{number}** - Generates a random fact about the number passed as the path parameter

## Setup

1. Clone the repository.
2. Set up a MySQL database and update the `application.properties` with your database credentials.
3. Build and run the Spring Boot application.

## Tools and Technologies

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- MockMvc, Mockito (for testing)

## How to Run the Application

1. Ensure you have Java and MySQL installed.
2. Clone the repository.
3. Set up the MySQL database:
    - Create a database for the application.
    - Update `application.properties` with the necessary database configurations.
4. Build the application using Gradle:
   ```bash
   ./gradlew bootrun
5. Postman collection is provided in the root folder.
