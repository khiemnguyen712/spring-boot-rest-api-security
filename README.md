# Student Management RESTful API Security

implementing **Spring Security**, providing authentication and authorization. 

---

## Technologies Used

- Spring Web
- Spring Security
- Spring Data JPA
- MySQL

---

## Project Structure

```
src/main/java
 └── com/example/studentmanagement
     ├── controller    # Handles REST API endpoints
     ├── entity        # Student entity, User entity, Role entity
     ├── dao           # JPA repositories for Student, User, and Role
     ├── service       # Business and security logic
     ├── security      # Spring Security configuration
     └── StudentManagementApplication.java # Main application class

src/main/resources
 ├── application.properties   # App and security configuration
 └── student_db_setup.sql     # SQL script to create and populate the database
```

---

## API Endpoints

| HTTP Method | Endpoint                | Description                     | Access  |
|-------------|--------------------- ---|---------------------------------|---------|
| GET         | `/api/students`         | Get all students                | USER, ADMIN |
| GET         | `/api/students/{id}`    | Get a student by ID             | USER, ADMIN |
| POST        | `/api/students`         | Create a new student            | ADMIN |
| PUT         | `/api/students/{id}`    | Update an existing student      | ADMIN |
| DELETE      | `/api/students/{id}`    | Delete a student                | ADMIN |
| POST        | `/api/auth/login`       | Authenticate user (Login)       | Public |

---

## Security Setup

- **Authentication**: Username and password stored in the database.
- **Authorization**:  
  - Users with role `STUDENT` can **view** student data (GET requests).
  - Users with role `TEACHER` can **create** and **update** students.
  - Users with role `PRINCIPLE` can perform all the above and **delete** students.
- **Password Encoding**: Passwords are stored securely using BCrypt hashing.

---

## Database Schema

**(Student, user role, login credentials included in `create-user-authority-table`)**

```sql
USE `student_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
('peppa','{noop}test123',1),
('madam','{noop}test123',1),
('aunty','{noop}test123',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('peppa','ROLE_EMPLOYEE'),
('madam','ROLE_EMPLOYEE'),
('madam','ROLE_MANAGER'),
('aunty','ROLE_EMPLOYEE'),
('aunty','ROLE_MANAGER'),
('aunty','ROLE_ADMIN');
```
