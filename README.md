# Smart Wedding Budget & Vendor Management System

Console project using Java 21, Maven, Hibernate ORM 6.6, and PostgreSQL. It does not use Spring Boot or lambda expressions.

## Setup
1. Create a PostgreSQL database named `marriagedb`.
2. Open `src/main/resources/hibernate.cfg.xml` and replace `YOUR_DATABASE_PASSWORD` with your local PostgreSQL password.
3. Open the project as a Maven project in IntelliJ IDEA.
4. Run `com.marriage.Main`.

Hibernate creates/updates the entity tables using `hibernate.hbm2ddl.auto=update`.

## Package responsibilities
- `model`: entity classes mapped to database tables.
- `view`: console menu, input and output.
- `service`: validation and business rules.
- `dao`: Hibernate database operations.
- `util`: shared Hibernate `SessionFactory`.
- `Main`: application entry point.

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

### Package Responsibilities

- **Main.java:** Starts the application.
- **model:** Contains entity classes mapped to database tables.
- **view:** Handles the console menu, user input, and output.
- **service:** Implements business rules and validation.
- **dao:** Performs database operations using Hibernate.
- **util:** Configures and provides the shared Hibernate `SessionFactory`.
- **hibernate.cfg.xml:** Stores Hibernate and PostgreSQL configuration.
- **pom.xml:** Defines Maven dependencies and project settings.
- **README.md:** Documents the project, setup, features, and execution instructions.

## Included Functions

- Add a worker and their first service offering.
- Add additional service offerings to an existing worker.
- Prevent duplicate service-package offerings for the same worker.
- View all service offerings sorted from lowest to highest price.
- Search service offerings by service name or category.
- Filter service offerings using a maximum budget (or a minimum and maximum budget, if both are implemented).
- Create a marriage booking with multiple selected service offerings.
- Check the total booking cost against the customer's budget.
- Save the customer, booking, and booking items within a database transaction.

## Not Implemented Yet

- Date-based worker availability and booking conflict checks.
- Editing and deleting workers or service offerings.
- Viewing booking history.
- Saving a worker and their first service offering atomically in a single transaction.

These features can be added in future versions.
