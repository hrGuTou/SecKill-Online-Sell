package com.learning.SecKillProject.service;

import com.learning.SecKillProject.model.Merchandise;
import com.learning.SecKillProject.model.OrderInfo;
import com.learning.SecKillProject.model.OrderRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;

@Service
public class OrderThread {

    @Autowired
    RedisTemplate redisTemplate;



    @Async
    public void executeAsyncTask() {
        OrderRecord orderRecord = (OrderRecord) redisTemplate.boundListOps(OrderRecord.class.getSimpleName()).rightPop();

        if(orderRecord!=null){

            //Merchandise item = (Merchandise)redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).get(Integer.toString(orderRecord.getMerchandise_id()));

            // perform purchase action


            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setUser_id(orderRecord.getUser_id());
            orderInfo.setMerchandise_id(orderRecord.getMerchandise_id());
            orderInfo.setOrder_status(0);
            orderInfo.setCreate_date(new Date());

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
        }

    }
}
