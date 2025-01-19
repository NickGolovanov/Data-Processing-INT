# **Documentation Folder**

This folder contains essential resources for understanding the project structure, database design, and API testing.

## **Contents**

### 1. **NefixClassDiagram.asta**
This class diagram represents the application's architecture, including:
- **Authentication**: How user authentication is handled.
- **Backups**: The backup mechanisms in place.
- **Packages**: Organization of classes, including:
    - **Repository classes**
    - **Entity classes**
    - **Service classes**

Use this diagram to understand the structure and relationships between key components in the system.

---

### 2. **NefixERD.asta**
The Entity Relationship Diagram (ERD) illustrates the database schema, including:
- Tables and their relationships
- Primary and foreign keys
- Data flow and interactions

This diagram is essential for understanding the database structure and how data is organized and interconnected.

---

### 3. **Postman Collections**
There are two Postman files included for API testing:

#### **API scenario testing.postman_collection.json**
- Designed for scenario-based testing of the APIs.
- Helps validate real-world usage scenarios of the application.
- **Note:** This collection can be executed simultaneously using Postman's collection runner.

#### **Data Processing.postman_collection.json**
- Used for contract testing to ensure API responses comply with predefined schemas.
- Focuses on verifying the structure and correctness of the API's output.
- **Note:** This collection must be executed **one request at a time**. Running the requests simultaneously will result in errors due to dependency or data conflicts.

---

### 4. **Swagger Documentation**
We also provide **Swagger Documentation** for detailed API specifications. Swagger serves as an interactive tool for exploring and testing the API endpoints. It provides:
- A list of all available endpoints.
- Details about request parameters and response schemas.
- Real-time testing functionality.

#### **How to Access Swagger Documentation:**
1. Ensure the application is running.
2. Open your web browser and navigate to:  
   **`http://localhost:8080/swagger-ui/index.html`**.
3. Use the interactive interface to explore endpoints and test API functionality.

Swagger documentation simplifies the process of understanding and using the API, making it easier to test and integrate.

---

## **How to Import Postman Collections and Run Tests**

1. Open **Postman**.
2. Click on the **Import** button in the top-left corner.
3. Select the desired file (`.postman_collection.json`) from this folder.
4. Click **Import** to add the collection to Postman.
5. For **Scenario Testing**:
    - Use the **Collection Runner** to execute all requests simultaneously.
6. For **Contract Testing**:
    - Execute each request **one by one** to avoid errors.
7. Configure the necessary **environment variables** (if applicable) before running the tests.

---

This documentation serves as a comprehensive guide to help you understand the project's structure, database, and how to perform API tests efficiently, with the additional benefit of Swagger for detailed API exploration. a comprehensive guide to help you understand the project's structure, database, and how to perform API tests efficiently, with the additional benefit of Swagger for detailed API exploration.