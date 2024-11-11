# Paper_Improvement

## 1. Project Title

**Performance Optimization with AES Encryption in Automatic Data Encryption Systems for Large-Scale Databases**

## 2. Project Description

This project aims to enhance the encryption system for sensitive data in large-scale databases by replacing the DES (Data Encryption Standard) algorithm with the more secure and efficient AES (Advanced Encryption Standard) algorithm. The original system utilized DES, which has limitations in security and efficiency when dealing with large datasets.

The update to AES provides improved security and performance, making the system more robust for real-world applications that require handling large volumes of sensitive data. This README will guide users through setting up the updated system, which now features AES encryption for improved protection and faster data processing.

## 3. Original Paper Overview

The original system design is based on the following paper:
- **Reference**: Zhang, X., Hu, H., & Xu, Y. (2024). The Design and Implementation of an Automatic Encryption and Decryption System for Sensitive Words in Databases Based on Spring Boot. *2024 5th International Seminar on Artificial Intelligence, Networking, and Information Technology (AINIT)*, IEEE. DOI: 10.1109/AINIT61980.2024.10581490. [Link to paper](https://ieeexplore.ieee.org/document/10581490)

## 4. Problem Statement

DES, although functional, is no longer adequate for large-scale applications due to its 56-bit key length, which is vulnerable to brute-force attacks. It also struggles with performance in large datasets.

**Proposed Improvement**: The project replaces DES with AES, which supports key sizes of 128, 192, and 256 bits, offering significantly enhanced security and performance.

## 5. Impact of Improvement

Switching to AES brings the following benefits:
- **Increased Security**: AES provides a stronger encryption mechanism, making it more resilient to modern cryptographic attacks.
- **Enhanced Performance**: AES is optimized for faster encryption and decryption, making it suitable for large datasets and high-throughput environments.

## 6. Project Scope

- **Algorithm Update**: Replace DES with AES and configure the system to handle large datasets efficiently.
- **Performance Comparison**: Measure and compare encryption and decryption times between DES and AES to quantify the improvements.

## 7. Technology Stack

- **Languages**: Java
- **Framework**: Spring Boot, Spring Data JPA
- **Encryption Libraries**: Java Cryptography Extension (JCE) for AES implementation
- **Database**: MySQL
- **Tools**: SLF4J for logging

---

## 8. Setup

### Prerequisites
- Java 11+
- Maven 3.x
- A MySQL or PostgreSQL database (or a database of your choice) for storing encrypted data
- Postman or any API testing tool

### Installation Steps:

1) **Clone the Repository**
    ```bash
    git clone https://github.com/your-repo/automaticed.git
    cd automaticed
    ```

2) **Set Up the Database**
    Create a database named `capstoneDatabase`. Update the `application.properties` file with your database connection details:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/capstoneDatabase
    spring.datasource.username=your_db_user
    spring.datasource.password=your_db_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3) **Build the Project**
    ```bash
    mvn clean install
    ```

4) **Run the Application**
    ```bash
    mvn spring-boot:run
    ```

---

## 9. API Endpoints

### 1) **Encrypt Data (AES)**
Encrypt sensitive data using the AES algorithm.

- **Endpoint**: `/encrypt`
- **Method**: `GET`
- **Parameters**:
  - `plaintext` (String): The text to be encrypted.
  - `algorithm` (String): The encryption algorithm (AES).
- **Example**: `/encrypt?plaintext=HelloWorld&algorithm=AES`

### 2) **Decrypt Data (AES)**
Decrypt AES-encrypted data.

- **Endpoint**: `/decrypt`
- **Method**: `GET`
- **Parameters**:
  - `ciphertext` (String): The encrypted text to be decrypted.
  - `algorithm` (String): The decryption algorithm (AES).
- **Example**: `/decrypt?ciphertext=GeXlTVTmmwZZ2FKFmUCVpQ==&algorithm=AES`

### 3) **Save Orders**
Save multiple orders with randomly generated `user_name` values, encrypted before saving to the database.

- **Endpoint**: `/saveAll`
- **Method**: `GET`
- **Parameters**:
  - `count` (Integer): Number of random orders to generate and save.
- **Example**: `/saveAll?count=100`

### 4) **Get Order by ID**
Retrieve the decrypted `user_name` for a specific order.

- **Endpoint**: `/get`
- **Method**: `GET`
- **Parameters**:
  - `orderId` (Long): The ID of the order to retrieve.
- **Example**: `/get?orderId=100`

### 5) **Get Paginated Orders**
Retrieve a paginated list of orders.

- **Endpoint**: `/getAllByPage`
- **Method**: `GET`
- **Parameters**:
  - `pageNo` (int): The page number to retrieve.
  - `pageSize` (int): The size of the page.
- **Example**: `/getAllByPage?pageNo=0&pageSize=10`

### 6) **Save Orders Manually**
Manually save a list of orders with their details.

- **Endpoint**: `/saveByOneself`
- **Method**: `POST`
- **Request Body**: A list of `OrderEntity` objects in JSON format.
- **Example**: `/saveByOneself`

---

## 10. How It Works

1. **Encryption and Decryption**: The `AESSensitiveConverter` class, as shown in the updated code, is used to encrypt and decrypt sensitive information using the AES algorithm.
2. **Order Management**: Orders are saved and retrieved via the `OrderService`, which interacts with the database. Sensitive information, such as `user_name`, is encrypted before storage and decrypted upon retrieval.

---

## 11. License

This project is licensed under the MIT License. See the `LICENSE` file for details.
