package com.learning.SecKillProject.service;

import com.learning.SecKillProject.mapper.MerchandiseMapper;
import com.learning.SecKillProject.mapper.OrderInfoMapper;
import com.learning.SecKillProject.model.Merchandise;
import com.learning.SecKillProject.model.OrderInfo;
import com.learning.SecKillProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    @Autowired
    OrderInfoMapper orderInfoMapper;



    //create new order
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

}
