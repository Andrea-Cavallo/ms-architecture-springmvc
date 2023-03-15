# ms-architecture-springmvc
Leveraging cutting-edge technologies such as the Spring MVC framework, microservice architecture, MongoDB, API-Cloud Gateway, Sleuth, Zipkin, Spring Security, and Eureka

# Services
The application consists of the following microservices:

Api-Cloud-Gateway
Config-Server
Order-Service
Payment-Service
Product-Service
Service-Registry (Eureka)
User-Service

# Technologies & Dependencies
The following technologies and dependencies are used in this project:

Spring Boot: The foundation for building production-ready Spring applications
Spring Cloud: Tools for developing common patterns in distributed systems
MongoDB: A flexible, horizontally scalable, high-performance NoSQL database
Sleuth: Distributed tracing for Spring Cloud applications
Zipkin: A distributed tracing system for gathering timing data
Eureka: Service discovery and registration for Spring Cloud applications
Spring Security: Authentication and access control for Spring applications
Okta: User management and authentication provider
Lombok: A library to reduce boilerplate code in Java applications
Spring Boot Test: A testing framework for Spring Boot applications

# How to Run the Application
Prerequisites
Java 11 or higher
Maven
MongoDB


git clone https://github.com/Andrea-Cavallo/ms-architecture-springmvc.git
cd online-marketplace-platform

Start the MongoDB instance.
mongod

Start the Service-Registry (Eureka).
bash

cd service-registry
mvn spring-boot:run
Start the Config-Server.

cd config-server
mvn spring-boot:run

Start the remaining microservices (Order-Service, Payment-Service, Product-Service, and User-Service).
cd order-service
mvn spring-boot:run

cd payment-service
mvn spring-boot:run

cd product-service
mvn spring-boot:run

cd user-service
mvn spring-boot:run

Finally, start the Api-Cloud-Gateway.

cd api-cloud-gateway
mvn spring-boot:run
The application is now running, and you can access the API endpoints through the Api-Cloud-Gateway at http://localhost:8080.
