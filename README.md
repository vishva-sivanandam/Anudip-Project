# Smart Wedding Budget & Vendor Management System

A console-based application built using Java 21, Maven, Hibernate ORM 6.6, and PostgreSQL. 
## Setup

1. Create a PostgreSQL database named `marriagedb`.
2. Open `src/main/resources/hibernate.cfg.xml` and replace `YOUR_DATABASE_PASSWORD` with your local PostgreSQL password.
3. Open the project in IntelliJ IDEA as a Maven project.
4. Run `com.marriage.Main` to start the application.

Hibernate creates or updates the entity tables automatically using `hibernate.hbm2ddl.auto=update`.

## Project Structure

```text
MarriageWorkingManagementSystem/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── marriage/
        │           ├── Main.java
        │           ├── model/
        │           │   ├── Worker.java
        │           │   ├── ServiceOffering.java
        │           │   ├── Customer.java
        │           │   ├── Booking.java
        │           │   └── BookingItem.java
        │           ├── view/
        │           │   ├── MarriageMenu.java
        │           │   ├── WorkerInput.java
        │           │   ├── BookingInput.java
        │           │   └── OfferDisplay.java
        │           ├── service/
        │           │   ├── WorkerService.java
        │           │   ├── ServiceOfferingService.java
        │           │   └── BookingService.java
        │           ├── dao/
        │           │   ├── WorkerDAO.java
        │           │   ├── ServiceOfferingDAO.java
        │           │   └── BookingDAO.java
        │           └── util/
        │               └── HibernateUtil.java
        └── resources/
            └── hibernate.cfg.xml
```

## Package Responsibilities

- **Main.java:** Entry point that starts the application.
- **model:** Contains entity classes mapped to database tables.
- **view:** Handles the console menu, user input, and output.
- **service:** Implements business rules and validation.
- **dao:** Performs database operations using Hibernate.
- **util:** Configures and provides the shared Hibernate `SessionFactory`.
- **hibernate.cfg.xml:** Contains Hibernate and PostgreSQL configuration.
- **pom.xml:** Defines Maven dependencies and project settings.
- **README.md:** Documents the project structure, setup, features, and execution instructions.

## Implemented Features

- Add a worker and their first service offering.
- Add additional service offerings to an existing worker.
- Prevent duplicate service-package offerings for the same worker.
- Display service offerings sorted by price, from lowest to highest.
- Search service offerings by service name or category.
- Filter service offerings by a maximum budget or a minimum and maximum price range, where supported.
- Create a marriage booking with multiple selected service offerings.
- Prevent selecting multiple packages from the same worker in one booking.
- Check worker availability for the selected marriage date against existing confirmed bookings.
- Prevent booking the same worker for multiple confirmed bookings on the same date, subject to the application's availability-check limitations.
- Calculate the combined cost of selected services and validate it against the customer's budget.
- Save the customer, booking, and booking items within a database transaction.

## Not Yet Implemented

- Editing and deleting workers or service offerings.
- Viewing booking history.
- Saving a worker and their first service offering atomically within a single database transaction.

## Technologies Used

- Java 21
- Maven
- Hibernate ORM 6.6
- PostgreSQL
- IntelliJ IDEA

## Notes

- The application uses Hibernate directly without Spring Boot.
- The project uses traditional Java syntax without lambda expressions.
- Database credentials must be configured locally and should not be committed to a public repository.
- The `hibernate.hbm2ddl.auto=update` setting is intended for development; production databases should use a controlled schema migration strategy.
