# SecKillProject
Backend Restful API simulates handling high concurrent QPS by using multithreading and caching

## Project Status
Under maintain
- Completed: User management
- Completed: Order service (single thread)
- Completed: Implemented Redis caching
- Completed: Implemented multi thread to handle high incoming queries  

## Tech Stack
- Springboot + Redis + MySQL
- JMeter

## Handling High Concurrent Incoming Queries
- Using ```@Async``` and ```ThreadPoolTaskExecutor``` to complete background tasks asynchronously.
- Using Redis caching to prevent high IO pressure for MySQL database.
- Using ```Synchronize()``` keyword to ensure thread-safety.
- Using Jmeter to simulate concurrent queries.

## JMeter Testing

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

## API Usage

See comments in ```src/main/java/com.learning.SecKillProject/SecKillProject.java```


