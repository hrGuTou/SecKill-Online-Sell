# SecKillProject
Backend Restful API simulates handling high concurrent QPS by using multithreading and caching

## Project Status
Under maintain
- Completed: User management
- Completed: Order service (single thread)
- Completed: Implemented Redis caching
- Completed: Implemented multi-thread to handle high incoming queries  

## Tech Stack
- Springboot + Redis + MySQL
- JMeter

## Handling High Concurrent Incoming Queries
- Using ```@Async``` and ```ThreadPoolTaskExecutor``` to complete background tasks asynchronously.
- Using Redis caching to prevent high IO pressure for MySQL database.
- Using ```Synchronize()``` keyword to ensure thread-safety.
- Using Jmeter to simulate concurrent queries.

## JMeter Testing
- Setting for concurrent queries (1000 users)
![alt text](https://github.com/hrGuTou/SecKillProject/blob/master/src/main/resources/img/JmeterSetting.png)
- HTTP request to api
![alt text](https://github.com/hrGuTou/SecKillProject/blob/master/src/main/resources/img/test.png)
- Merchandise stock counts (Only 10 stock available)
![alt text](https://github.com/hrGuTou/SecKillProject/blob/master/src/main/resources/img/database.png)
- Issue: oversold 
![alt text](https://github.com/hrGuTou/SecKillProject/blob/master/src/main/resources/img/oversold.png)
<br/><br/>
- Analyze: Since tasks are running under multiple threads, we need to guarantee that stock=stock-1 is atomic 
```
Merchandise item = (Merchandise) redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).get(Integer.toString(orderRecord.getMerchandise_id()));
                if(item != null){
                    if(item.getMerchandise_stock()>0){
                        item.setMerchandise_stock(item.getMerchandise_stock() - 1); //NOT ATMOIC STATEMENT
                        redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).put(Integer.toString(item.getMerchandise_id()), item);
                        redisTemplate.boundHashOps(OrderInfo.class.getSimpleName()).put(LocalTime.now()+Integer.toString(orderRecord.getUser_id()), orderInfo);

                    }
                }
```
- Solution: Using synchronize keyword to ensure the block of code is executed all in one.
```
synchronized (OrderThread.class) {
                Merchandise item = (Merchandise) redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).get(Integer.toString(orderRecord.getMerchandise_id()));
                if(item != null){
                    if(item.getMerchandise_stock()>0){
                        item.setMerchandise_stock(item.getMerchandise_stock() - 1);
                        redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).put(Integer.toString(item.getMerchandise_id()), item);
                        redisTemplate.boundHashOps(OrderInfo.class.getSimpleName()).put(LocalTime.now()+Integer.toString(orderRecord.getUser_id()), orderInfo);

                    }
                }
            }
```
- Only 10 items are sold
![alt text](https://github.com/hrGuTou/SecKillProject/blob/master/src/main/resources/img/thread-safe.png)
<br/><br/>
- Other possible solution: Using optimistic locking, ex: CAS with version


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


