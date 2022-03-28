# Project Description
A backend API which allows the creation users to interact with logging reimbursements for a company. 
New accounts can be created and Admins can update users to be able to use the application. 
Employees can create and lookup reimbursements while Finance Managers can approve or deny them.

## Technologies Used
- Java
- Maven
- JDBC
- SQL
- Servlets
- Postman
- PostgreSQL
- JUnit
- Mockito

## Features
* A new employee or new finance manager can request registration with the system
* An admin user can approve or deny new registration requests
* A registered employee can authenticate with the system by providing valid credentials
* An authenticated employee can view and manage their pending reimbursement requests
* An authenticated employee can view their reimbursement request history 
* An authenticated employee can submit a new reimbursement request
* An admin user can deactivate user accounts, making them unable to log into the system
* An admin user can reset a registered user's password

## To-do list:

Implement Prism and other features

## Getting Started
1. From Command line or Linux shell make sure git is installed: git clone https://github.com/220207-java-enterprise/Andrew_foundations_project.git
2. Create a properties file with db-url=jdbc:postgresql://localhost:5432/ a username and password as well as a "salt" for token services
3. Make sure that startup.sh is running in a command line(Windows/Mac) or catalina.sh run(Linux)
4. Open your IDE and run mvn tomcat7:deploy inside your cloned directory
5. On postman interact with data through your localhost:8080/


## License
This project uses the following license: All rights reserved through Revature.
