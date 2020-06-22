package com.learning.SecKillProject.service;

import com.learning.SecKillProject.mapper.MerchandiseMapper;
import com.learning.SecKillProject.mapper.OrderInfoMapper;
import com.learning.SecKillProject.model.Merchandise;
import com.learning.SecKillProject.model.OrderInfo;
import com.learning.SecKillProject.model.Result;
import com.learning.SecKillProject.model.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    MerchandiseMapper merchandiseMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    MerchandiseService merchandiseService;



    //create new order (single thread, directly from MYSQL)
    public OrderInfo createOrder(User user, Merchandise merchandise){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUser_id(user.getUserId());
        orderInfo.setMerchandise_id(merchandise.getMerchandise_id());
        orderInfo.setOrder_status(0);
        orderInfo.setCreate_date(new Date());

        // save to database
        orderInfoMapper.saveOrder(orderInfo);

        return orderInfo;
    }

    //create new order, from redis

    public Result saveOrder(User user, Merchandise merchandise){
        //get merchandise from redis
        Merchandise item = (Merchandise) redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).get( Integer.toString( merchandise.getMerchandise_id()));

        if(item!=null && item.getMerchandise_stock()>0){
            // merchandise exists and stock count > 0
            // perform purchase action
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setUser_id(user.getUserId());
            orderInfo.setMerchandise_id(merchandise.getMerchandise_id());
            orderInfo.setOrder_status(0);
            orderInfo.setCreate_date(new Date());

            redisTemplate.boundHashOps(OrderInfo.class.getSimpleName()).put(Integer.toString(user.getUserId()), orderInfo);

            item.setMerchandise_stock(item.getMerchandise_stock()-1);

            if(item.getMerchandise_stock() <=0){
                //sold out
                //remove from redis cache
                //update database
                merchandiseMapper.setOutOfStock(item);

                redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).delete(Integer.toString(item.getMerchandise_id()));
            }else{
                //update cache, return success

                redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).put(Integer.toString(merchandise.getMerchandise_id()), item);

            }



        }else{
            return new Result(false, "Out of stock");
        }

        return new Result(true, "Placed order");
    }

}
