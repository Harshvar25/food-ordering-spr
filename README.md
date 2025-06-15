# Spring Boot Food Ordering System

A simple and functional food ordering system built using Spring Boot, Spring JDBC, Java, JSP, and MySQL. This project demonstrates full-stack development with clear separation between admin and customer functionalities.

## ✨ Features
- 🔐 **Admin Panel**
  - Add, view, and remove food items
  - View and remove customer accounts
- 👤 **Customer Panel**
  - Sign up, sign in, view food menu
  - Add to cart, update quantity, remove items
  - Secure logout and session handling
- 🛒 **Cart System**
  - Real-time quantity updates
  - Total price calculations
- 📊 **Dashboards**
  - Separate interfaces for admin and users

## 🧰 Tech Stack
- **Java**
- **Spring Boot**
- **Spring JDBC**
- **JSP & Servlets**
- **MySQL**
- **Maven**

## 🧩 Project Structure & Design

This project follows the **MVC (Model-View-Controller)** architecture for better maintainability and separation of concerns:

- 🗃️ **DAO Layer** – Responsible for direct database interactions using Spring JDBC  
- 🧠 **Service Layer** – Contains the core business logic and acts as a bridge between DAO and Controller  
- 🕹️ **Controller Layer** – Handles client requests, invokes services, and returns the appropriate views  
- 🎨 **View Layer** – JSP pages form the frontend, dynamically rendering data sent by controllers

The codebase is modularly organized into packages such as `dao`, `service`, `controller`, and `entity` for clarity and scalability.

## 🚀 How to Run
1. Clone the repository  
   `git clone https://github.com/Harshvar25/YOUR-REPO-NAME.git`
2. Open the project in IntelliJ IDEA or Eclipse as a Maven project
3. Configure your MySQL credentials in `applicationContext.xml`
4. Run the application from the main class containing `@SpringBootApplication`
5. Access the app via browser (typically at `http://localhost:8080`)

## 🛠️ Database Setup

All the SQL table creation queries are available in [`schema.sql`](docs/schema.sql).  
Make sure to import this into your MySQL database before running the application.

## 👨‍💻 Author
**Harshvardhan Singh Bais**  
🔗 [GitHub](https://github.com/Harshvar25)  
📧 harshbais87@gmail.com
