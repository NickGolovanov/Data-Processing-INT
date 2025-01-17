# Data Processing INT

## Purpose of This README

The purpose of this README file is to provide an overview of the **Data Processing INT** project. It serves as the primary resource for setting up, troubleshooting, and understanding the project's structure and technology choices. The README includes detailed setup instructions, information about the technologies used, features of the project, and guidance on troubleshooting issues during set up.

## Description

This project demonstrates the development of an **API** using **Java**, **Spring Boot**, **PostgreSQL**, **Docker**, and **JWT** for authentication. The goal was to build a portable, secure, and efficient API architecture, focusing on learning new technologies, optimizing processes, and ensuring smooth API functionality and interaction.

## Features

- **Docker** for environment portability.
- **PostgreSQL** with **pgAdmin** for database management.
- **Flyway** for handling database migrations.
- **JWT** authentication for secure API access.

## Technologies

- **Java** with **Spring Boot** for backend development.
- **PostgreSQL** database for storing and managing data.
- **pgAdmin** simplifies database interaction.
- **Docker** for setting up a portable development environment.
- **Flyway** for database schema management.
- **JWT** for stateless, secure authentication.

## Setup Instructions

1. Remove existing project volumes:
   ```bash
   docker-compose down -v
   ```

2. Rebuild the project without cache:
   ```bash
   docker-compose build --no-cache
   ```

3. Start the containers:
   ```bash
   docker-compose up
   ```

## Troubleshooting

In case something doesn't work:

1. **Check Docker Logs**:
    - Run `docker-compose logs` to review the logs and identify any errors or issues.

2. **Verify PostgreSQL Container**:
    - Ensure the PostgreSQL container is running. You can check with:
      ```bash
      docker ps
      ```
    - If the container isn't running, try restarting it:
      ```bash
      docker-compose restart postgres
      ```

3. **Rebuild Docker Containers**:
    - If any containers are not behaving as expected, rebuild them:
      ```bash
      docker-compose up --build
      ```

4. **Database Migrations**:
    - If the database schema isn't set up correctly, ensure Flyway is running migrations. Check if the database tables and schemas are correctly created.

5. **Verify JWT Authentication**:
    - If there’s an issue with JWT tokens, check token expiration times and ensure tokens are properly passed and validated.

6. **Port Conflicts**:
    - If you're unable to access the app or database, check if other applications are using the same ports. You can modify the `docker-compose.yml` to change port mappings.

7. **Network Issues**:
    - If there's a network issue between services, ensure that the services are correctly linked in `docker-compose.yml` under the `networks` section.

8. **Rebuild with No Cache**:
    - If changes aren’t reflected or there are caching issues, rebuild with no cache:
      ```bash
      docker-compose build --no-cache
      ```

## Why We Chose These Technologies

### Java & Spring Boot
- **Why Java?** We chose Java for its robust ecosystem and reliability, with **Spring Boot** simplifying backend development and offering a flexible framework.

### Database
- **Why PostgreSQL?** The team wanted to learn and gain experience with PostgreSQL due to its performance and scalability.
- **Why pgAdmin?** pgAdmin is used to simplify interaction with PostgreSQL by providing a graphical user interface for managing the database.
- **Why Flyway?** Flyway is necessary because Spring Boot does not automatically handle SQL queries for creating views, stored procedures, or triggers, and Flyway helps manage migrations effectively.
- **Why not use views in APIs?** Spring Boot doesn't offer built-in support for working with views, which is why views are not directly called within the API.

### Docker
- **Why Docker?** Docker provides a portable setup for both development and production, ensuring consistent environments and simplifying deployment.

### Backup Strategy
- **Why Full DB Backup?** A full database backup is scheduled daily at 2:00 AM for security purposes to ensure data integrity and easy recovery in case of a failure.
- **Why WAL (Write-Ahead Log) Archive?** The WAL archive is stored every 5 minutes, ensuring that in case of a database failure, incremental recovery can be achieved, as each archive weighs only 5 MB.
- **Why Two Types of Backups?** A full database backup ensures full recovery in case of data loss, while the WAL archive provides step-by-step recovery for smaller issues.

### Authentication
- **Why JWT and 1-day Validity?** JWT tokens are set to expire after 1 day. There's no industry standard for token expiration, but this period offers a balance between security and usability.
- **Why JWT over Session-Based Authentication?** JWT is simpler and more efficient for stateless API interaction, reducing overhead on the server side.
- **Why Not Store JWT Tokens?** Storing JWT tokens can pose security risks, so they are kept client-side only and are not persisted.

### API Endpoints
- **Why POST Requests Always Return a Response?** POST requests return responses to confirm that the action has been processed, enhancing reliability and user feedback.
- **Why UPDATE Requests Always Return a Response?** Similarly, update requests return responses to indicate whether the operation was successful or failed.
- **Why Implement Generics?** Generics were implemented to reduce code duplication, improve maintainability, and make the system more scalable and reusable.

### Testing
The project utilizes Postman for testing, which includes the following:

- **Scenario Testing:** Ensures the system works as expected in various use cases.
- **Contract Testing:** Verifies that the APIs meet the agreed-upon contract between the backend and frontend teams.

You can access the Postman collection through the following link:

### Documentation

In addition to Postman testing, the project includes comprehensive **documentation** that provides a detailed overview of the system’s architecture. This includes:

- **Class Diagram**: A visual representation of the system’s structure and relationships between the components.
- **ERD (Entity-Relationship Diagram)**: A diagram illustrating how entities are related within the database.
- **Swagger Documentation**: The Swagger API documentation, which is automatically generated and accessible via the API. It includes detailed information about the request and response bodies for each endpoint.

These documents will be available in the project’s **documentation folder**, where you can find detailed technical specifications and visual aids for understanding the system.

## License

This project is open-source. Contributions and suggestions for improvements are welcome!