package com.learning.SecKillProject.service;

import com.learning.SecKillProject.config.IdWorker;
import com.learning.SecKillProject.mapper.MerchandiseMapper;
import com.learning.SecKillProject.mapper.OrderInfoMapper;
import com.learning.SecKillProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

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

    @Autowired
    OrderThread orderThread;



    private IdWorker idworker;


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

    public Result saveOrder(User user, Merchandise merchandise) throws ExecutionException, InterruptedException {

        //check if user already waiting or already placed order
//        boolean inline = redisTemplate.boundSetOps(SysConfig.CONST_user_id_pre+user.getUserId()).isMember(Integer.toString(user.getUserId()));
//        if(inline){
//            return new Result(false, "Already placed order");
//        }



        //get merchandise from redis
        Merchandise item = (Merchandise) redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).get( Integer.toString( merchandise.getMerchandise_id()));

        //get merchandise from queue

//        Integer merchandise_id = (Integer) redisTemplate.boundListOps(SysConfig.CONST_merchandise_pre+Integer.toString(merchandise.getMerchandise_id())).rightPop();
//
//
//        if(merchandise_id == null){
//            return new Result(false, "Out of stock");
//        }

        if(item == null){
            return new Result(false, "Out of stock");
        }

        if(item.getMerchandise_stock() <= 0){
            redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).delete(Integer.toString(item.getMerchandise_id()));
        }

        //redisTemplate.boundSetOps(SysConfig.CONST_user_id_pre+user.getUserId()).add(Integer.toString(user.getUserId()));

        OrderRecord orderRecord = new OrderRecord(merchandise.getMerchandise_id(), user.getUserId());
        redisTemplate.boundListOps(OrderRecord.class.getSimpleName()).leftPush(orderRecord);

        //Future<Result> task = orderThread.executeAsyncTask();
        orderThread.executeAsyncTask();
        //System.out.println(item.getMerchandise_stock());
        return new Result(true, "Order initialized...");
    }

}
