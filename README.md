


# Orbit - Digital Game Distribution Platform

<p align="center"> <img src="res/images/commons/orbit_logo.png" alt="Orbit Logo" width="200"/> </p>

Orbit is a Java-based desktop application designed to simulate a digital game distribution platform. This project was developed for University of Pavia as part of the "Programmazione a Oggetti e Ingegneria del Software" course exam.

---

## Project Overview

**Orbit** allows users to browse a catalog of video games, purchase them using a simulated payment system, and manage their personal library. It supports multiple user roles, including standard Users (Gamers), Publishers, and Administrators, each with specific permissions.

---

## Key Features

### User Account Management
* **Authentication**: Login and Registration system.
* **Role-Based Different Actions**:
    * **User**: Can browse the store, purchase games, and access their library.
    * **Publisher**: Can upload and publish new games to the store.
    * **Admin**: Possesses system-wide management privileges.

### Store & Library
* **Shop Interface**: A visual grid displaying available games with cover images and pricing.
* **Purchasing System**: Implementation of payment processing via Debit Card logic.
* **Personal Library**: A persistent collection of purchased games associated with the user's account.
* **Review System**: Users can review with an up-vote/down-vote system the games they own, the  total score is stored in the database.

### Technical Functionality
* **Data Persistence**: All data (users, games, transactions, reviews) is stored locally using SQLite.
* **Discount System**: Logic to handle and apply discounts to game prices.
* **Gift Cards**: Support for redeeming gift codes for increasing the users account balance.

---

## Software Architecture & Design Patterns

The project follows a modular architecture to ensure scalability, maintainability ease of cooperation between team members.

* **Singleton Pattern**:
    * `SingletonAccountManager`: Manages the current user session to ensure only one active user state exists.
    * `SingletonDatabaseHelper`: Ensures a unique connection instance to the SQLite database to prevent resource conflicts.
* **Facade Pattern**:
    * `FacadeUserInterface`: Provides a simplified high-level interface to the complex subsystem of UI windows and views.
    * `FacadeDB`: Encapsulates complex database queries and DAO operations behind a clean interface.
* **Strategy Pattern**:
    * `IPaymentMethod`: Defines a common interface for payments, allowing for interchangeable payment methods.
* **MVC Principles**:
    * The project maintains a separation between the **Model** (Account, Game entities), **View** (Swing UI classes), and **Controller** logic.

---

## Technology Stack

* **Language**: Java (JDK 17 or higher recommended)
* **GUI Framework**: Java Swing / AWT
* **Database**: SQLite
* **Drivers**: sqlite-jdbc (version 3.51.2.0)
* **IDE**: Eclipse or IntelliJ IDEA

---

## Installation & Setup

Follow these steps to configure and run the project in your local environment.

### 1. Prerequisites
Ensure you have the following installed:
* **Java Development Kit (JDK)**: Version 17 or later.
* **IDE**: Eclipse IDE for Java Developers (recommended) or IntelliJ IDEA.

### 2. Import the Project
1.  Open your IDE.
2.  Select **File > Import > General > Projects from Folder or Archive**.
3.  Select the root folder `Orbit`.

### 3. Configure the Build Path
The project requires the SQLite JDBC driver to communicate with the database.
1.  Right-click the project folder in your IDE.
2.  Go to **Build Path > Configure Build Path**.
3.  Select the **Libraries** tab (or "Classpath").
4.  Click **Add JARs...** (or "Add JAR/Folder").
5.  Navigate to the `lib/` (or `bin/` depending on your structure) folder inside the project and select `sqlite-jdbc-3.51.2.0.jar`.
6.  Apply and Close.

### 4. Database Configuration
The application uses a local SQLite database file located at:
`res/database/OrbitDB.db`

* **Important**: Ensure the `res` folder is in the project root. The `SingletonDatabaseHelper` class looks for the connection string `jdbc:sqlite:res/database/OrbitDB.db`.
* If the database file is missing, the application creates the necessary tables automatically upon the first run, but you may need to populate it with initial data or use the provided SQL script (`SqlQueryForDbCreation.sql`) if available.

### 5. Running the Application
1.  Navigate to `src/it/unipv/posfw/orbit/main/`.
2.  Right-click `Main.java`.
3.  Select **Run As > Java Application**.

The Login window should appear. You can register a new account or log in if you have pre-existing credentials.

---

## Project Structure

```text
it.unipv.posfw.orbit
├── account      # User, Admin, Publisher roles and Account Manager
├── database     # Database connection (Singleton) and Facade
├── discount     # Logic for managing store discounts
├── exception    # Custom exceptions (e.g., UserNotFound, PaymentFailed)
├── game         # Game entities, Reviews, and Licenses
├── library      # Logic for managing user game libraries
├── main         # Application entry point (Main.java)
├── payment      # Payment interfaces and implementations
└── view         # UI Frames and Windows (Login, Shop, Library)
