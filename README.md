# Microservice architecture with Spring Boot


## _Using leveraging cutting-edge technologies such as the Spring MVC framework, microservice architecture, MongoDB, API-Cloud Gateway, Sleuth, Zipkin, Spring Security, and Eureka_


The application consists of the following microservices:

- Api-Cloud-Gateway
- Config-Server
- Order-Service
- Payment-Service
- Product-Service
- Service-Registry (Eureka)
- User-Service

## WHY APY-GATEWAY?

- Simplifying the client’s interaction with the microservices system by providing a single entry point.
- Providing a centralized location for implementing cross-cutting concerns such as logging, monitoring, and security.
- Reducing the complexity of the microservices system by hiding the implementation details of individual microservices.

## WHY CIRCUITBREAKER?


 
- A circuit breaker can be used to protect cross-service calls. 
- When a certain number of downstream resource requests fail to meet a certain threshold, a circuit breaker opens. 
- The system will quickly fail if the circuit breaker is open. 
- After some time, the client will send some requests to check whether the downstream service has been restored. 
- The request will be sent again once the health is restored if there is a normal response.


## TECHNOLOGIES & DEPENDENCIES


- Spring Boot: The foundation for building production-ready Spring applications
- Spring Cloud: Tools for developing common patterns in distributed systems
- MongoDB: A flexible, horizontally scalable, high-performance NoSQL database
- Sleuth: Distributed tracing for Spring Cloud applications
- Zipkin: A distributed tracing system for gathering timing data
- Eureka: Service discovery and registration for Spring Cloud applications
- Spring Security: Authentication and access control for Spring applications
- Okta: User management and authentication provider
- Lombok: A library to reduce boilerplate code in Java applications
- Spring Boot Test: A testing framework for Spring Boot applications

