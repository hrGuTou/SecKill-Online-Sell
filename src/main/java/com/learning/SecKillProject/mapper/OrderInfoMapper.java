package com.learning.SecKillProject.mapper;


import com.learning.SecKillProject.model.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoMapper {

    //create order, not paid yet
    @Insert("Insert into orderInfo (user_id, merchandise_id, order_status, create_date) VALUES (#{user_id},#{merchandise_id},#{order_status},#{create_date}) ")
    void saveOrder(OrderInfo orderInfo);



}
