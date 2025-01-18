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

You can run the application using one of the provided scripts based on your operating system:

- **For Windows**: Use the `start_project.ps1` script. Run it in PowerShell with:
  ```powershell
  ./start_project.ps1
  ```

- **For Unix-based systems**: Use the `start_project.sh` script. Ensure it has execute permissions:
  ```bash
  chmod +x start_project.sh
  ./start_project.sh
  ```

Ensure you have the necessary permissions and configurations before running the scripts.

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

7. **Rebuild with No Cache**:
    - If changes aren’t reflected or there are caching issues, rebuild with no cache:
      ```bash
      docker-compose build --no-cache
      ```

## API Documentation

Once the application is running, you can access the API documentation through Swagger UI. Swagger is an interactive API documentation tool that allows you to explore and test the API endpoints provided by the application. The documentation is available at the following URL:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Visit this page to view the list of available API endpoints, their request/response formats.

## Why We Chose These Technologies

### Java & Spring Boot
- **Why Java?** We chose Java for its robust ecosystem and reliability, with **Spring Boot** simplifying backend development and offering a flexible framework.

# **Database**

Our database strategy leverages PostgreSQL, supported by tools and practices designed for performance, ease of management, and scalability.

## **Why PostgreSQL?**
PostgreSQL was chosen for its:
- **Robust performance**
- **High scalability**
- **Learning opportunity**: The team wanted to gain hands-on experience with this powerful and widely-used database management system.

## **Why pgAdmin?**
pgAdmin simplifies the management of PostgreSQL by providing:
- **Graphical User Interface (GUI):** Makes interaction easier and more intuitive.
- **Monitoring and management tools:** Ensures efficient operation and oversight of the database.

## **Why Flyway?**
Flyway is essential for managing database migrations because:
- **Spring Boot limitations:** It does not automatically handle SQL for creating views, stored procedures, or triggers.
- **Migration management:** Flyway offers a reliable and streamlined way to handle these tasks effectively.

## **Why not use views directly in APIs?**
Views are not used directly in APIs because:
- **Lack of support in Spring Boot:** It does not natively support interaction with database views.
- **Separation of concerns:** Managing views outside the API maintains clean and modular design principles.

## **Database Users Setup**
To maintain role-based access and security, the following database users were created:

```sql
CREATE USER senior_user WITH PASSWORD 'securepassword';  
CREATE USER medior_user WITH PASSWORD 'securepassword';  
CREATE USER junior_user WITH PASSWORD 'securepassword';  
```

These users were created solely for testing purposes and are not intended to be used in real-life scenarios.

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

### Documentation

In addition to Postman testing, the project includes comprehensive **documentation** that provides a detailed overview of the system’s architecture. This includes:

- **Class Diagram**: A visual representation of the system’s structure and relationships between the components.
- **ERD (Entity-Relationship Diagram)**: A diagram illustrating how entities are related within the database.
- **Swagger Documentation**: The Swagger API documentation, which is automatically generated and accessible via the API. It includes detailed information about the request and response bodies for each endpoint.

These documents will be available in the project’s **documentation folder**, where you can find detailed technical specifications and visual aids for understanding the system.

## License

GNU General Public License v3.0.0
