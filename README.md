# SecKillProject
Backend Restful API simulates handling high concurrent QPS by using multithreading and caching

## Project Status
Under development
- Completed: User management
- Completed: Order service (single thread)
- Completed: Implemented Redis caching 

## Tech Stack
Springboot + Redis + RabbitMQ + MySQL

## Setup MySQL and Redis
Install MySQL and Redis<br/>
Locate `application.properties`
```
spring.datasource.url= {MYSQL connection url}
spring.datasource.username= {Username}
spring.datasource.password= {Password}
spring.redis.host=127.0.0.1
spring.redis.port={port}
redis.config.host=redis://127.0.0.1:{port}
```
