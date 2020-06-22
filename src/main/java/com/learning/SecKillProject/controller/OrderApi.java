package com.learning.SecKillProject.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.learning.SecKillProject.annotation.CurrentUser;
import com.learning.SecKillProject.annotation.LoginRequired;
import com.learning.SecKillProject.model.Merchandise;
import com.learning.SecKillProject.model.OrderInfo;
import com.learning.SecKillProject.model.Result;
import com.learning.SecKillProject.model.User;
import com.learning.SecKillProject.service.MerchandiseService;
import com.learning.SecKillProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@CrossOrigin(origins={"http://localhost:3000"})
public class OrderApi {

    @Autowired
    OrderService orderService;

    @Autowired
    RedisTemplate redisTemplate;

    Result result;

    @Autowired
    MerchandiseService merchandiseService;

    @LoginRequired
    @PostMapping("")
    public Object createOrder(@CurrentUser User user, @RequestBody Merchandise merchandise){
        JSONObject res = new JSONObject();

        Merchandise item = (Merchandise) redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).get( Integer.toString( merchandise.getMerchandise_id()));
        if(item == null){
            res.put("error", "No such item");
            return res;
        }

         //TODO: 加锁
        // using mysql

//        OrderInfo orderInfo = orderService.createOrder(user,item);
//        //如果下单成功再库存-1
//        if(orderInfo != null){
//            merchandiseService.reduceStock(item);
//            res.put("success", orderInfo);
//        }else{ //否则返回错误信息
//            res.put("error", "Order failed");
//        }
        //

        //using cache
        result = orderService.saveOrder(user, merchandise);
        if(result.isSuccess()){
            res.put("success", result.getMessage());
        }else{
            res.put("error", result.getMessage());
        }

        return res;
    }
}
