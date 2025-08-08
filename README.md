# 🏨 Hotel Management System – Java (Swing + JDBC)

This is a **Hotel Management System** developed using **Java Swing** for the GUI and **JDBC (Java Database Connectivity)** for backend database operations. The application helps manage room bookings, customer details, menu ordering, and bill generation in a simplified hotel environment.

---

## 📁 Project Structure

```
├── Main.java             # Entry point of the application
├── HotelGUI.java         # Graphical User Interface (Swing-based)
├── MenuItem.java         # Menu model with name and price
├── Database.java         # JDBC-based database connectivity
```

---

## 🚀 Features

* ✅ GUI-based hotel management
* ✅ Dynamic menu selection and ordering
* ✅ Real-time bill generation
* ✅ Database-driven room and customer data
* ✅ Modular code structure using MVC-like separation

---

## 🖼️ Screenshots

<img width="605" height="600" alt="image" src="https://github.com/user-attachments/assets/3d89a66f-aec9-428d-b274-d6801bd67679" />
<img width="600" height="606" alt="image" src="https://github.com/user-attachments/assets/31f75b48-cea6-4ee2-8c3a-fad324aab2a2" />
<img width="598" height="606" alt="image" src="https://github.com/user-attachments/assets/ac86b0ed-a1eb-4e65-a11c-079f5f4f9e72" />

---

## 🔧 Technologies Used

* **Java** (JDK 8+)
* **Swing** for GUI
* **JDBC** for database interaction
* **MySQL / SQLite** (based on your database)
* **NetBeans / IntelliJ IDEA / VS Code** (any IDE of your choice)

---

## 🛠️ Setup & Run Instructions

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/hotel-management-system.git
   cd hotel-management-system
   ```

2. **Set Up the Database**

   * Create a MySQL database (e.g., `hotel_db`)
   * Update the DB credentials in `Database.java`
   * Import tables (rooms, customers, menu, etc.) manually or via SQL scripts.

3. **Compile and Run**

   * Open the project in your preferred IDE
   * Compile and run `Main.java`

   **OR**

   ```bash
   javac *.java
   java Main
   ```

---

## 📝 Database Configuration

Update the following in `Database.java` as per your local setup:

```java
String url = "jdbc:mysql://localhost:3306/hotel_db";
String username = "your_username";
String password = "your_password";
```

Ensure your MySQL JDBC driver is in the classpath.

---

## 📦 Possible Enhancements

* Add user authentication (admin/staff)
* Export bills to PDF
* Add booking history with timestamps
* Add CRUD functionality for menu items and rooms

---

## 🤝 Contributing

Feel free to fork this repo and suggest features or improvements via pull requests.

---

## 📜 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.
