# 📊 Expense Tracker API

A backend **Expense Tracker REST API** built using **Spring Boot**, designed to manage expenses with full **CRUD operations**, **pagination**, **sorting**, **filtering**, and **global exception handling**.

This project demonstrates **clean architecture**, **best practices**, and **real-world backend concepts**.

---

## 🚀 Features

### ✅ Core Features
- Create, Read, Update, Delete (CRUD) expenses
- Fetch expense by ID
- Filter expenses by category
- Pagination & sorting support
- Monthly total calculation
- Category-wise expense summary

### ✅ Backend Best Practices
- Spring Data JPA
- Global Exception Handling (`@RestControllerAdvice`)
- Custom exception (`ExpenseNotFoundException`)
- Validation using `@Valid`
- Clean layered architecture

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **H2 In-Memory Database**
- **Lombok**

---

## 📌 API Capabilities

- RESTful API design
- Pageable & sortable endpoints
- Centralized exception handling
- Production-style service layering
- In-memory database for easy setup

---

## 🔗 Supported APIs

- **POST** `/api/expenses`
- **GET** `/api/expenses`
- **GET** `/api/expenses` (supports pagination, sorting & expense category filtering)
- **GET** `/api/expenses/{id}`
- **PUT** `/api/expenses/{id}`
- **DELETE** `/api/expenses/{id}`
- **GET** `/api/expenses/monthly-total`
- **GET** `/api/expenses/category-summary`

---

## 🧠 Learning Outcomes

This project helped in understanding:
- REST API design principles
- Pagination & sorting with Spring Data JPA
- Custom exception handling
- Validation and error response modeling
- Structuring Spring Boot applications cleanly

---

## 👨‍💻 Author

**Soumyadri**

Backend project built to strengthen Spring Boot & REST API skills.
