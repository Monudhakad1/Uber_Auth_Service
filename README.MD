# Uber Auth Service 🚗

A microservice-based authentication system for Uber-like ride-hailing applications built with Spring Boot, Spring Security, and MySQL.

## 🌟 Features

- **Passenger Registration**: Secure signup with encrypted passwords
- **Spring Security Integration**: JWT-ready authentication framework
- **Database Management**: Flyway migrations for version control
- **RESTful API**: Clean endpoint structure
- **Password Encryption**: BCrypt hashing for security
- **JPA/Hibernate**: Entity relationship management

## 🛠️ Tech Stack

- **Backend**: Spring Boot 3.x
- **Security**: Spring Security 6.x
- **Database**: MySQL 8.x
- **Migration**: Flyway
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Password Hashing**: BCrypt

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/uberauthservices/uber_auth_services/
│   │   ├── Configuration/
│   │   │   └── SpringSecurity.java          # Security configuration
│   │   ├── controller/
│   │   │   └── AuthController.java          # REST endpoints
│   │   ├── Dto/
│   │   │   ├── PassengerDto.java           # Data transfer objects
│   │   │   └── PassengerSignUpRequestDto.java
│   │   ├── models/
│   │   │   ├── BaseModel.java              # Base entity class
│   │   │   ├── Passenger.java              # Passenger entity
│   │   │   ├── Driver.java                 # Driver entity
│   │   │   ├── Booking.java                # Booking entity
│   │   │   └── BookingStatus.java          # Booking status enum
│   │   ├── Repositories/
│   │   │   └── PassengerRepository.java    # Data access layer
│   │   └── service/
│   │       └── AuthService.java            # Business logic
│   └── resources/
│       ├── application.properties          # App configuration
│       └── db/migration/                   # Database migrations
│           ├── V1__intial_db.sql
│           ├── V2__add_phone_number_to_driver.sql
│           ├── V3__add_address_to_driver.sql
│           └── V4__add_phone_email_password_topassengers.sql
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- MySQL 8.0+
- IDE (IntelliJ IDEA/Eclipse/VS Code)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/uber-auth-service.git
cd uber-auth-service
```

2. **Setup MySQL Database**
```sql
CREATE DATABASE Uber_db_local;
```

3. **Configure Database**
   Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/Uber_db_local
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. **Build and Run**
```bash
mvn clean install
mvn spring-boot:run
```

The service will start on `http://localhost:7475`

## 📚 API Documentation

### Authentication Endpoints

#### Passenger Signup
```http
POST /api/v1/auth/signup/passenger
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "securePassword123",
  "phoneNumber": "+1234567890"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "+1234567890",
  "createdAt": "2024-01-15T10:30:00.000+00:00"
}
```

#### Sign In (In Development)
```http
GET /api/v1/auth/signin
```

## 🗄️ Database Schema

### Core Tables

- **passenger**: User information with encrypted passwords
- **driver**: Driver details and license information
- **booking**: Ride booking records
- **booking_review**: Review and rating system

### Entity Relationships

- `Passenger` → `Booking` (One-to-Many)
- `Driver` → `Booking` (One-to-Many)
- `Booking` → `BookingReview` (One-to-One)

## 🔧 Configuration

### Security Configuration
- CSRF disabled for API usage
- Public endpoints: `/api/v1/auth/signup/*`, `/api/v1/auth/signin`
- All other endpoints require authentication

### Database Configuration
- JPA show-sql enabled for development
- Flyway baseline-on-migrate enabled
- Lazy loading configured

## 🛣️ Roadmap

### Phase 1 (Current)
- [x] Basic project structure
- [x] Passenger signup
- [x] Database migrations
- [x] Security configuration

### Phase 2 (Next)
- [ ] JWT token implementation
- [ ] Signin endpoint completion
- [ ] Driver signup functionality
- [ ] Input validation
- [ ] Error handling

### Phase 3 (Future)
- [ ] Refresh token mechanism
- [ ] Role-based authorization
- [ ] Email verification
- [ ] Password reset functionality
- [ ] Rate limiting
- [ ] API documentation (Swagger)

## 🧪 Testing

### Manual Testing with cURL

**Signup Test:**
```bash
curl -X POST http://localhost:7475/api/v1/auth/signup/passenger \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "password": "password123",
    "phoneNumber": "+1234567890"
  }'
```

## 🔒 Security Features

- **Password Encryption**: BCrypt with salt
- **SQL Injection Protection**: JPA/Hibernate parameterized queries
- **CSRF Protection**: Disabled for stateless API
- **Input Validation**: DTO-based validation (to be enhanced)

## 📝 Development Notes

### Known Issues
1. Signin endpoint returns hardcoded response
2. No JWT token generation implemented
3. Missing input validation
4. No duplicate email/phone validation

### Code Quality
- Clean architecture with separation of concerns
- Proper use of DTOs for data transfer
- Builder pattern implementation
- Lombok for boilerplate reduction

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@monudhakad1](https://github.com/monudhakad1)
- Email: monudhakad055@gmail.com

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Spring Security for robust authentication
- MySQL team for reliable database system
- Flyway for database version control

---

**Status**: 🚧 Active Development | **Version**: 1.0.0-SNAPSHOT | **Last Updated**: January 2024