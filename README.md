# SecKillProject
Backend Restful API simulates handling high concurrent QPS by using multithreading and caching

## Project Status
Under development
- Completed: User management
- Completed: Order service (single thread)

## Tech Stack
Springboot + Redis + RabbitMQ + MySQL

## Setup MySQL
Locate `application.properties`
```
spring.datasource.url= {MYSQL connection url}
spring.datasource.username= {Username}
spring.datasource.password= {Password}
```
