# 🛒 E-Commerce Spring Boot Application

A full-featured RESTful e-commerce backend built with Spring Boot, Spring Security, JWT, and MySQL.

---

## 📁 Project Structure

```
src/main/java/com/ecommerce/
├── controller/        → REST API endpoints
│   ├── AuthController.java
│   ├── ProductController.java
│   ├── CartController.java
│   └── OrderController.java
├── service/           → Business logic
│   ├── AuthService.java
│   ├── ProductService.java
│   ├── CartService.java
│   └── OrderService.java
├── repository/        → Database operations
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   ├── CartRepository.java
│   └── OrderRepository.java
├── model/             → Database entities
│   ├── User.java
│   ├── Product.java
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Order.java
│   └── OrderItem.java
├── dto/               → Request/Response objects
│   ├── AuthDTO.java
│   └── ProductDTO.java
├── config/            → Security & JWT config
│   ├── SecurityConfig.java
│   ├── JwtUtil.java
│   └── JwtFilter.java
└── exception/         → Error handling
    └── GlobalExceptionHandler.java
```

---

## ⚙️ Setup Instructions

### 1. Prerequisites
- Java 17+
- Maven
- MySQL

### 2. Create Database
```sql
CREATE DATABASE ecommerce_db;
```

### 3. Update application.properties
```properties
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

### 4. Run the project
```bash
mvn clean install
mvn spring-boot:run
```

---

## 🔗 API Endpoints

### Auth
| Method | URL | Description | Auth Required |
|--------|-----|-------------|---------------|
| POST | /api/auth/register | Register new user | No |
| POST | /api/auth/login | Login & get JWT token | No |

### Products
| Method | URL | Description | Auth Required |
|--------|-----|-------------|---------------|
| GET | /api/products | Get all products | No |
| GET | /api/products/{id} | Get product by ID | No |
| GET | /api/products/search?name= | Search products | No |
| GET | /api/products/category/{cat} | Filter by category | No |
| POST | /api/products | Create product | ADMIN only |
| PUT | /api/products/{id} | Update product | ADMIN only |
| DELETE | /api/products/{id} | Delete product | ADMIN only |

### Cart
| Method | URL | Description | Auth Required |
|--------|-----|-------------|---------------|
| GET | /api/cart | View cart | Yes |
| POST | /api/cart/add?productId=&quantity= | Add to cart | Yes |
| DELETE | /api/cart/remove/{productId} | Remove item | Yes |
| DELETE | /api/cart/clear | Clear cart | Yes |

### Orders
| Method | URL | Description | Auth Required |
|--------|-----|-------------|---------------|
| POST | /api/orders/place?shippingAddress= | Place order | Yes |
| GET | /api/orders/my-orders | My orders | Yes |
| GET | /api/orders/{id} | Order details | Yes |
| PUT | /api/orders/{id}/status?status= | Update status | ADMIN only |

---

## 🔐 How to use JWT

1. Register or Login → you get a token
2. For protected routes, add this header:
```
Authorization: Bearer YOUR_TOKEN_HERE
```

---

## 🧪 Test with Postman

### Step 1 — Register
```json
POST /api/auth/register
{
  "username": "john",
  "email": "john@example.com",
  "password": "password123",
  "fullName": "John Doe"
}
```

### Step 2 — Login
```json
POST /api/auth/login
{
  "username": "john",
  "password": "password123"
}
```

### Step 3 — Use the token in Postman
- Go to Authorization tab → Bearer Token → paste token

---

## 🛠️ Tech Stack
- **Java 17**
- **Spring Boot 3.2**
- **Spring Security + JWT**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Maven**
