# Electronic Store Management System

## Overview
The **Electronic Store Management System** is a Java-based application using **JavaFX** for a graphical user interface. It enables efficient management of store operations, including inventory tracking, sales processing, and user role-based access control.

## Implementation Details

- **Programming Language:** Java  
- **GUI Framework:** JavaFX  
- **Data Storage:** Text and binary files  
- **Architecture:** Model-View-Controller (MVC)  

## Features

### 🔹 **Core Architecture & Design**
- **MVC Architecture**: Ensures separation of concerns and modular development.  
- **Object-Oriented Design**: Implements encapsulation, inheritance, polymorphism, abstract classes, interfaces, and generics.  
- **File Handling**: Uses both text and binary file storage for data persistence.  
- **Validation & Exception Handling**: Implements input validation using string functions, regular expressions, and structured error handling with custom exceptions.  
- **Factory Classes**: Manages user creation and system-wide operations such as stock updates and transaction tracking.  
- **Proxy Classes**: Mocks database operations during testing to enhance maintainability and compliance with the **Single Responsibility Principle (SRP)**.  

### 🔹 **User Roles & Permissions**
| Role          | Username | Password |
|--------------|------------|----------|
| **Administrator** | `admin` | `admin` |
| **Manager** | `man` | `123` |
| **Cashier** | `cas` | `123` |

**Role-based capabilities:**  
- **Administrator:** Manages users, stock, and financial reports.  
- **Manager:** Restocks inventory, monitors store performance, and tracks revenue.  
- **Cashier:** Handles transactions, processes bills, and prints receipts.  

### 🔹 **Reporting & Statistics**
- Generates **sales summaries** and **stock reports** for better decision-making.  

### 🔹 **Interactive JavaFX GUI**
- User-friendly design with intuitive menus for seamless navigation.  

### 🔹 **Authentication System**
- **Role-based login** for secure access control.  

## 👥 **Team Credits**
| Name | Contribution |
|------|-------------|
| **Ester Shumeli** | Team Leader + **Controller & Repository packages** |
| **Abdulaziz Bezan** | **Model & Util packages** |
| **Enea Cane** | **View & Validator packages** |
| **Evisa Nela** | **Service & Statistics packages** |

## 📁 **Project Contents**
- **All source files** (`.java`)  
- **Compiled `.jar` or executable**  
- **README.md** (this file)  
---
 **How to Run the Project:**  
1. **Ensure you have JavaFX installed.**  
2. Compile and run the application using `javac` and `java`.  
3. Use the provided credentials to log in and explore the system.  

