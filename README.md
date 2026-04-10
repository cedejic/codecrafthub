# README.md — CodeCraftHub

## Project Overview

**CodeCraftHub** is a simple Spring Boot REST API designed for learning backend development fundamentals without using a database. Instead, it stores data in a local JSON file (`courses.json`).

This project focuses on:

- Learning REST API fundamentals
- Understanding CRUD operations
- Working with JSON file persistence
- Practicing Spring Boot architecture
- Implementing validation and logging

It is intentionally simple and beginner-friendly while still following real-world backend patterns.

---

## Features

- Full CRUD REST API
- JSON file storage (no database required)
- Auto-increment course IDs
- Automatic file creation if missing
- Input validation
- Status validation using enum
- Logging using `@Slf4j`
- Jackson JSON mapping
- Lombok for reduced boilerplate code
- Beginner-friendly code structure

---

## Technology Stack

- Java 17
- Spring Boot
- Maven
- Jackson
- Lombok
- REST API

---

## Installation Instructions

### 1) Clone the repository

```bash
git clone https://github.com/cedejic/codecrafthub.git
cd codecrafthub
```

### 2) Open the project in IDE

Recommended IDE:

- IntelliJ IDEA
- VS Code
- Eclipse

Make sure:

- Java 17 is installed
- Maven is installed
- Lombok plugin is enabled in your IDE

---

## How to Run the Application

### Option 1 — Using Maven

```bash
mvn spring-boot:run
```

### Option 2 — Using IDE

Run:

```
CodeCraftHubApplication.java
```

---

## Default Server URL

```
http://localhost:8080
```

---

## JSON File Storage

The application automatically creates this file when first started:

```
courses.json
```

Example file content:

```json
[
  {
    "id": 1,
    "name": "Spring Boot",
    "description": "Learn REST APIs",
    "target_date": "2026-06-01",
    "status": "In Progress",
    "created_at": "2026-04-11 10:30:00"
  }
]
```

---

# API Endpoint Documentation

Base URL:

```
/api/courses
```

---

## 1) Create Course

### Endpoint

```
POST /api/courses
```

### Request Body

```json
{
  "name": "Spring Boot",
  "description": "Learn REST APIs",
  "target_date": "2026-06-01",
  "status": "In Progress"
}
```

### Response

```json
{
  "id": 1,
  "name": "Spring Boot",
  "description": "Learn REST APIs",
  "target_date": "2026-06-01",
  "status": "In Progress",
  "created_at": "2026-04-11 10:30:00"
}
```

---

## 2) Get All Courses

### Endpoint

```
GET /api/courses
```

### Response

```json
[
  {
    "id": 1,
    "name": "Spring Boot",
    "description": "Learn REST APIs",
    "target_date": "2026-06-01",
    "status": "In Progress",
    "created_at": "2026-04-11 10:30:00"
  }
]
```

---

## 3) Get Course by ID

### Endpoint

```
GET /api/courses/{id}
```

### Example

```
GET /api/courses/1
```

---

## 4) Update Course

### Endpoint

```
PUT /api/courses/{id}
```

### Example

```json
{
  "name": "Spring Boot Advanced",
  "description": "Deep dive into Spring",
  "target_date": "2026-07-01",
  "status": "Completed"
}
```

---

## 5) Delete Course

### Endpoint

```
DELETE /api/courses/{id}
```

### Example

```
DELETE /api/courses/1
```

---

## Valid Status Values

The `status` field must be exactly one of:

```
Not Started
In Progress
Completed
```

---

# Testing the API

You can test the API using:

- Postman
- curl
- Insomnia
- Browser (for GET requests)

---

## Example curl Commands

### Create Course

```bash
curl -X POST http://localhost:8080/api/courses \
-H "Content-Type: application/json" \
-d '{
  "name": "Docker",
  "description": "Learn containers",
  "target_date": "2026-08-01",
  "status": "Not Started"
}'
```

---

### Get All Courses

```bash
curl http://localhost:8080/api/courses
```

---

# Troubleshooting Guide

## Problem — Application fails to start

Possible causes:

- Java version is incorrect
- Maven not installed
- Lombok not enabled

Solution:

```bash
java -version
mvn -version
```

Ensure:

```
Java 17
```

---

## Problem — Lombok errors in IDE

Solution:

Enable Lombok plugin.

IntelliJ:

```
Settings → Plugins → Lombok
```

Then enable:

```
Build → Annotation Processing
```

---

## Problem — courses.json not created

Possible causes:

- No write permission
- Application did not start successfully

Solution:

Check logs for:

```
Creating courses.json file
```

---

## Problem — Invalid status error

Make sure status is exactly:

```
Not Started
In Progress
Completed
```

This is case-sensitive.

---

## Problem — Port already in use

Error:

```
Port 8080 already in use
```

Solution:

Change port in:

```
application.properties
```

Example:

```properties
server.port=8081
```

---

# Future Improvements

Possible next features:

- Swagger / OpenAPI documentation
- Global exception handler
- Pagination
- Filtering by status
- Docker support
- Database integration (PostgreSQL)
- Authentication (JWT)
- Unit testing

---

# Author

CodeCraftHub — Learning Project

