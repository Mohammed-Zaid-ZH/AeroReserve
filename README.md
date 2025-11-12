#  AeroReserve — Flight Booking Management System

A full-stack web application for managing **flight reservations** and **seat bookings**, built using **Spring Boot**, **Spring JPA**, **MySQL**, and a clean **HTML/CSS/JavaScript** frontend.  

This project demonstrates practical implementation of REST APIs, CRUD operations, layered architecture (Controller-Service-Repository), and basic role-based access between Users and Admins.

---

##  Features

###  User
- Register and Login  
- View available flights  
- Book seats instantly  
- View personal booking history  

###  Admin
- Add new flights  
- View all existing flights  
- View all user bookings (who booked what and how many seats)  
- (Spring Security integration planned for role-based access)

---

##  Tech Stack

| Layer | Technology |
|--------|-------------|
| **Frontend** | HTML, CSS, Bootstrap, JavaScript |
| **Backend** | Java, Spring Boot, Spring Data JPA, Hibernate |
| **Database** | MySQL |
| **Build Tool** | Maven |
| **Version Control** | Git & GitHub |

---

## 🗂️ Project Structure
AeroReserve/
├── src/main/java/com/aeroreserve/
│ ├── controller/ # REST controllers (User, Flight, Booking)
│ ├── model/ # Entity classes
│ ├── repository/ # Spring Data JPA interfaces
│ ├── service/ # Business logic
│ └── AeroReserveApplication.java
│
├── src/main/resources/
│ ├── static/
│ │ ├── css/style.css
│ │ ├── js/main.js
│ │ ├── index.html
│ │ ├── login.html
│ │ ├── register.html
│ │ └── admin.html
│ └── application.properties
│
├── pom.xml
└── README.md
