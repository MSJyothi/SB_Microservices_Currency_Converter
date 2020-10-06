# SB_Microservices_Currency_Converter

A  microservice spring boot application using spring cloud

Currency converter service interacts with currency exchange service for getting the exchange value and converts the amount from one format to another

Implementation Details

- Centralized Microservice Configuration with Spring Cloud Config Server

- Using Spring Cloud Bus to exchange messages about Configuration updates

- Simplify communication with other Microservices using Feign REST Client

- Implement client side load balancing with Ribbon

- Implement dynamic scaling using Eureka Naming Server and Ribbon

- Implement API Gateway with Zuul
