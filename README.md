![markdown logo](car-sharing-logo.jpg)

# 🚘 Car Rental Service

This project aims to solve the issues faced by a manual car-sharing service in tracking cars, rentals, users, and 
payments. The goal is to implement an online car rental management system that streamlines the administrative tasks and 
enhances the user experience.

## 🎯Key Features

`User Management:` Facilitates user registration, login, and role-based authorization, allowing different user levels 
to have appropriate access and capabilities within the service. Users can securely create accounts, log in to the system, 
and perform actions based on their assigned roles.

`Exception Handling:` Implements robust exception handling with descriptive messages, enhancing the user experience by 
providing clear and informative error messages. This makes it easier to identify and resolve issues, ensuring a smooth 
user journey.

`Flexible Endpoints:` Offers multiple endpoints with user and admin access, catering to the specific functionalities and 
operations required by different user types. Users and administrators can interact with the system through designated 
endpoints, ensuring efficient and secure access.

💳 `Secure Payment Integration:` Integrates with the Stripe payment service, ensuring secure and reliable transactions for 
car rentals. Users can make payments conveniently using various payment methods supported by Stripe, enhancing trust and 
user satisfaction.

📨 `Real-time Notifications:` Employs a Telegram bot to provide real-time notifications to users. Users receive timely 
updates about their rental status, payment reminders, and any changes in the condition of the cars. This proactive 
communication ensures users stay informed throughout their car rental experience.

🚗 `Efficient Car Booking and Management:` Streamlines the car booking process, enabling users to easily search, book, and 
manage their rentals. Users can view car availability, select rental periods, and conveniently return cars when they are 
done. The system ensures accurate tracking of rental details, making the process seamless and user-friendly.

These key features collectively contribute to an enhanced user experience, efficient car rental management, and a secure 
payment environment within the service.

## ⚙️ Install (via Docker)

    * Ensure Docker is installed and running.
    * Clone the repository.
    * Set up the necessary environment variables in .env file.
    * Build the project using the command: 'mvn clean package'.
    * Run the command: `docker-compose up'.
Now you are ready for testing the application using [Swagger.](
http://localhost:6868/swagger-ui/index.html#/)


## 📁 Architecture
The project follows the following package structure:
* `bot:` Contains Telegram bot settings.
* `config:` Contains configuration files.
* `controller:` Contains the controllers.
* `dto:` Contains data transfer objects used for data encapsulation and transfer between different layers of the 
application.
* `exception:` Contains custom exception classes.
* `model:` Contains the database model used to represent data entities and map database records to Java objects.
* `repository:` Contains the data access layer responsible for accessing and manipulating data in the database.
* `security.jwt:` Contains security settings using the JWT authentication.
* `service:` Contains the services responsible for business logic and interaction between controllers and repositories.

## 🛠 Used technologies:

- Java 17 version
- Spring Boot 3.1.0 version
- Springdoc OpenAPI (Swagger) 2.1.0 version
- Liquibase
- MySQL
- Stripe Java 22.21.0 version
- jjwt 0.9.1 version
- Telegram Bots	5.7.1 version
- Docker 22.18.0

By implementing the features of this online car rental system, the service can automate processes, provide 
real-time information, and offer a more convenient and efficient experience for both users and administrators.
Please ensure all necessary configurations are correctly set up before running the application. If you encounter any issues, 
refer to the project's documentation or contact the development team for assistance.