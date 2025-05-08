# Payment Simulator

This is a payment simulation project for a payment processing system. The application allows for user registration, login, payment initiation, and confirmation. It uses JWT for authentication and Spring Boot for the backend.

## Features

- User Registration
- User Login (JWT-based)
- Initiate Payment
- Confirm Payment

## Prerequisites

- JDK 17 or higher
- Maven (or Gradle)
- A tool like Postman or cURL to test APIs
- Git (optional for cloning the repository)

## ⚙️ How to Set Up the Project

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/radhika9-sonvane/payment-simulator.git
    cd payment-simulator
    ```

2. **Set up MySQL Database:**

    - Create a new MySQL database by running the following SQL command:

    ```sql
    CREATE DATABASE payment_db;
    ```

3. **Configure Database Connection:**

    - Open `src/main/resources/application.properties`
    - Set the correct database URL, username, and password:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/payment_db
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    ```

4. **Build the Project:**

    - If you're using **Maven**, run the following command to build the project:

    ```bash
    mvn clean install
    ```

5. **Run the Application:**

    - You can run the Spring Boot application using Maven:

    ```bash
    mvn spring-boot:run
    ```

    - Alternatively, you can run it from your IDE (e.g., IntelliJ or Eclipse).

## Testing the APIs

You can use Postman or cURL to test the API endpoints. Here's how you can test each endpoint:

### a) Register User:

- **Endpoint:** `POST /api/auth/register`
- **Body:**

    ```json
    {
        "name": "John Doe",
        "email": "john.doe@example.com",
        "password": "securepassword"
    }
    ```

### b) Login:

- **Endpoint:** `POST /api/auth/login`
- **Body:**

    ```json
    {
        "email": "john.doe@example.com",
        "password": "securepassword"
    }
    ```

- **Response:** On successful login, you'll get a JWT token in the response body.

### c) Initiate Payment:

- **Endpoint:** `POST /api/payments/initiate`
- **Body:**

    ```json
    {
        "amount": 1500,
        "currency": "INR",
        "description": "Flight Booking",
        "userEmail": "john.doe@example.com"
    }
    ```

### d) Confirm Payment:

- **Endpoint:** `POST /api/payments/confirm`
- **Body:**

    ```json
    {
        "payment_id": "generated-id",
        "status": "SUCCESS"
    }
    ```

## Access the Application:

Once the server is running, you can access the application locally at:

```text
http://localhost:8080
