# Student Management System

This project is a Student Management System built with Spring Boot, Angular, and MySQL. It allows for managing student data including CRUD operations.

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Node.js and npm for Angular version 19
- Bootstrap
- MySQL

## Setting Up the Database

Ensure you have MySQL installed and running. You can create a new database for this project.

## Configure Database Connection

Go to the application properties file in 'Student\Springboot\src\main\resources' and change the following properties according to your MySQL setup:

- spring.datasource.username=your_mysql_username
- spring.datasource.password=your_mysql_password

## Project Structure

- src/main/java: Contains the Java source code

- src/main/resources: Contains configuration files like application.properties

- src/main/resources/static: Contains static web assets for Angular

## Running the Application

### Backend (Spring Boot)
Navigate to the Student\Springboot directory and run the following command:

- mvn spring-boot:run

### Frontend (Angular)
Navigate to the Student\Angular\Student directory and run the following commands:

- npm install
- npm install bootstrap
- npm start



## The Angular application will be available at http://localhost:4200