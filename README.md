Bank API Documentation

Overview
The Bank API, developed using Spring Boot and Docker, facilitates transactions, deposits, and withdrawals within a banking system. It employs various database relations for efficient management of customer accounts and transactions.

Getting Started
To use the Bank API:
Prerequisites
- Docker installed on your system
- Postman for testing the API endpoints

Installation
1. Clone the repository from GitHub.
2. Navigate to the project directory.
3. Run the following command to build and run the Docker container:
    ```
    docker-compose up --d
    ```
4. Once the container is up, access the API at http://localhost:8080.

Testing
1. Import the provided Postman collection (`bank-system.postman_collection.json`) into Postman.
2. Test the endpoints to ensure proper functionality.

Endpoints

Withdraw Money
Withdraws a specified amount from the account.
- URL: /api/transaction/withdraw/{id}
- Method: PUT
- Path Parameters:
  - id: ID of the account to withdraw from.
- Request Body:
    ```json
    { "amount": 100.00 }
    ```
- Description: Initiates a withdrawal transaction for the specified account.

Deposit Money
Deposits a specified amount into the account.
- URL: /api/transaction/deposit/{id}
- Method: PUT
- Path Parameters:
  - id: ID of the account to deposit into.
- Request Body:
    ```json
    { "amount": 100.00 }
    ```
- Description: Initiates a deposit transaction for the specified account.

Get All Transactions
Retrieves details for all transactions.
- URL: /api/transaction
- Method: GET
- Description: Retrieves details for all transactions made.

Bank Controller

Create Bank
Creates a new bank.
- URL: /api/bank
- Method: POST
- Request Body: BankDto object representing the new bank.
- Description: Creates a new bank with the provided details.

Get All Bank Accounts
Retrieves details for all bank accounts.
- URL: /api/bank
- Method: GET
- Returns: List of BankDto objects representing all bank accounts.
- Description: Retrieves details for all bank accounts.

Get Total Transaction Fee Amount
Retrieves the total transaction fee amount for a bank.
- URL: /api/bank/total-transaction-fee/{id}
- Method: GET
- Path Parameters:
  - id: ID of the bank to retrieve the total transaction fee amount for.
- Returns: Total transaction fee amount as a double.
- Description: Retrieves the total transaction fee amount for the specified bank.

Get Total Transfer Amount
Retrieves the total transfer amount for a bank.
- URL: /api/bank/total-transfer/{id}
- Method: GET
- Path Parameters:
  - id: ID of the bank to retrieve the total transfer amount for.
- Returns: Total transfer amount as a double.
- Description: Retrieves the total transfer amount for the specified bank.

Account Controller

Add Account
Adds a new account.
- URL: /api/account/addAccount
- Method: POST
- Request Body: AccountDto object representing the new account.
- Returns: HTTP status code 200 if the account is added successfully.
- Description: Adds a new account with the provided details.

Get Account Balance by ID
Retrieves the balance of an account by its ID.
- URL: /api/account/{id}
- Method: GET
- Path Parameters:
  - id: ID of the account to retrieve the balance for.
- Returns: AccountDto object representing the account balance.
- Description: Retrieves the balance of the account with the specified ID.

Project Structure
The project follows a structured approach to organize its components:
- Controllers: Contains the API endpoints for handling HTTP requests.
- Models: Defines the data models used in the application.
- Mappers: Maps entities to DTOs and vice versa.
- DTOs: Data Transfer Objects for transferring data between layers.
- Repositories: Interfaces for interacting with the database.
- Helpers: Utility classes and methods to assist in various tasks.
- Exceptions: Custom runtime exceptions for handling errors gracefully.
-JUnit Testing: To ensure the robustness of the Bank API, JUnit testing is employed for comprehensive testing of functionalities and error handling mechanisms.

Database Relations
The API utilizes the following database relations:
- Many-to-One: Relation between customers and accounts.
- One-to-Many: Relation between an account and its transactions.

Note

Database Relations
The API utilizes the following database relations:
- Many-to-One: Relation between customers and accounts.
- One-to-Many: Relation between an account and its transactions.

Note
This API is for demonstration purposes only and may not be suitable for production use without further enhancements and security measures.

For any issues or inquiries, please contact erblinzulfaj9@gmail.com.

Thank you for including me in this challenge!
