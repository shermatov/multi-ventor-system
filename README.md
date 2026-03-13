# Multi-Vendor Marketplace Platform

A scalable backend platform for a **multi-vendor e-commerce marketplace** built with **Spring Boot**.
The system allows multiple shop owners to manage their stores while customers can browse products, add them to carts, and place orders.

This project demonstrates backend architecture used in real marketplace systems such as Amazon, Etsy, or eBay.

---

## Features

### User Management

* User registration and authentication
* JWT-based security
* Role-based access (Admin, Shop Owner, Customer)

### Shop Management

* Create and manage shops
* Shop owners can manage their own store
* Admin can manage all shops

### Product Catalog

* Product management
* Categories and brands
* Product pagination and filtering

### Cart System

* Add products to cart
* Update quantity
* Remove items

### Order Management

* Create orders from cart
* Order status management
* Order history for users

### Payment

* Payment domain structure prepared
* Ready for integration with payment providers

### Reviews

* Customers can review products
* Product rating system

---

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* PostgreSQL
* Flyway (Database migrations)

### API

* RESTful API
* Swagger / OpenAPI documentation

### Infrastructure

* Docker
* Environment based configuration
* CORS configuration
* JWT authentication

---

## Project Architecture

The application follows a layered architecture:

```
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Domain modules include:

* User
* Shop
* Product
* Category
* Brand
* Cart
* Order
* Payment
* Review

---

## Database Structure (Simplified)

```
User
  │
  └── Shop
        │
        └── Product
              ├── Category
              └── Brand

User
  └── Cart
        └── CartItem
              └── Product

User
  └── Order
        └── OrderItem
              └── Product

Product
  └── Review
```

---

## API Documentation

Swagger UI is available at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Running the Project

### 1. Clone the repository

```
git clone https://github.com/YOUR_USERNAME/multi-vendor-platform.git
cd multi-vendor-platform
```

### 2. Configure environment variables

```
DB_URL=jdbc:postgresql://localhost:5432/marketplace
DB_USER=postgres
DB_PASS=password
JWT_SECRET=your-secret-key
```

### 3. Run the application

```
./mvnw spring-boot:run
```

or

```
mvn spring-boot:run
```

---

## Future Improvements

* Product search
* Product images storage
* Payment gateway integration (Stripe)
* Order tracking
* Email notifications
* Recommendation system

---

## Author

**Aibek Shermatov**

Computer Engineer | Java Backend Developer

Poland 🇵🇱 | Kyrgyzstan 🇰🇬
