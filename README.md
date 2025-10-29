# 📚 Library Management System

## Project Description 📖

This project is a library management system developed in Java using a PostgreSQL database. The project allows for the management of information about books, authors, publishers, and library sections, with user interaction handled via the console. Database interaction is performed via JDBC, and all data is persisted in PostgreSQL.

---

## Database Structure 🗄️

Tables:
- **book** (id, title, author_id, genre, year, section_id, publisher_id, stock)
- **author** (id, name, email, phone, nationality, birth_year, book_count)
- **publisher** (id, name, city, founding_year)
- **section** (id, name, location, capacity)

The SQL script for table creation and initial data insertion can be found in the `sql/` folder, in the `database_setup.sql` file.

---

## Implemented Features — Phase 2 ✅

✔️ **Database Connection**:
- The application uses a JDBC connection to PostgreSQL, managed centrally by a dedicated class (in the db folder).

✔️ **Table Creation and Population**:
- The required tables were created and populated in pgAdmin, based on an SQL script (provided as a separate file so it can be run quickly on other database instances if needed).

✔️ **CRUD Operations and Queries**:
- The application allows adding, deleting, modifying (updating), and displaying data for each entity.
- These operations are exposed through the options available in the interactive console menu.

✔️ **Singleton Services**:
- We structured the services responsible for CRUD operations for each entity (Book, Author, Publisher, Section) as Singletons, ensuring only one instance of each exists in the application to efficiently manage database interactions.

✔️ **Action Logging**:
- All actions performed by the user via the interactive menu are logged to a CSV file, along with a timestamp. This functionality helps in tracking application activity.

✔️ **Entity Relationships**:
- We correctly ensured the relationships between tables (a book has an associated author, publisher, and section) at both the database level and within the application.

✔️ **Interactive Console Menu** for accessing features:
- Display, add, delete books
- Search book by title
- Modify (Update) title
- Update authors / publishers / sections, etc.

---
