**Booking Management System**

This Booking Management System is designed to handle reservations and property blocks. It facilitates the creation, modification, and cancellation of bookings, as well as the management of property blocks by property owners or administrators.

### Features:

1. **User Management:**
    - The system automatically creates two test users during startup:
        - Admin User: Has the ability to create property blocks.
        - Regular User: Used for general bookings.

2. **Booking Operations:**
    - Users can create, update, and cancel bookings.
    - Bookings include information such as start and end dates, property details, and booking status.

3. **Property Blocks:**
    - Admin users can create blocks, indicating periods during which no bookings are allowed.
    - These blocks can be used for personal property use or property maintenance.

4. **Conflict Prevention:**
    - The system prevents conflicts between bookings and property blocks.
    - Before creating or updating a booking, the system checks for overlapping periods with existing blocks.

### Getting Started:

To run the application locally, follow these steps:

1. Clone the repository: `git clone <repository_url>`
2. Navigate to the project directory: `cd <project_directory>`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

### Technologies Used:

- Java
- Spring Boot
- Hibernate
- Maven
- H2 Database (for testing)

### Notes:

- Ensure that the application is running locally before testing the endpoints.
- Users with admin privileges can create property blocks, while regular users can make bookings.

Feel free to explore and use this system for managing bookings and property blocks efficiently!