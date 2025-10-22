Project Title: Fitness Management System with AI Recommendations
Role: Full-Stack Developer
Duration: October 2025
Tech Stack:
Backend: Java 17, Spring Boot, Spring Cloud (Eureka, Gateway, Config Server), Kafka, MongoDB, MySQL, JWT, WebFlux Security
Frontend: React, Redux Toolkit, Tailwind CSS
AI Integration: Google Gemini API

🔹 Overview

Developed a cloud-native fitness management system that allows users to register, log in securely, track their fitness activities, and receive personalized AI-generated workout suggestions through integration with Google Gemini API. The architecture follows microservices principles with independent modules communicating via REST and Kafka events.

🔹 Key Features

Service Discovery & Centralized Config: Implemented Spring Cloud Eureka Server for dynamic service registration and discovery, and a Config Server integrated with GitHub for externalized configuration management.

API Gateway: Designed a Spring Cloud Gateway with JWT authentication and route-level filtering for secure routing between microservices.

Microservices:

User Service: MySQL-based service handling authentication, registration, and profile management.

Activity Service: MongoDB-based service for storing fitness logs and publishing Kafka events.

AI Service: Consumes Kafka events and interacts with Google Gemini API to generate intelligent fitness recommendations stored in MongoDB.

Security: Implemented JWT-based authentication with reactive WebFlux configuration ensuring only authorized users can access protected routes.

Frontend: Built a responsive React + Redux interface for user onboarding, login, and dashboard visualization of activity data, integrated via API Gateway endpoints.

Scalability & Resilience: Enabled loose coupling via Kafka topics and Eureka registration to maintain service availability even during partial failures.

🔹 Impact

Reduced inter-service coupling by 40% through event-driven design.

Improved security with centralized token validation at API Gateway.

Delivered a production-ready architecture demonstrating full Spring Cloud microservices capabilities with AI integration.
