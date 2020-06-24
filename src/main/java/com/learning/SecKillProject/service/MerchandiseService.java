package com.learning.SecKillProject.service;

import com.learning.SecKillProject.config.SysConfig;
import com.learning.SecKillProject.mapper.MerchandiseMapper;
import com.learning.SecKillProject.model.Merchandise;
import com.learning.SecKillProject.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchandiseService {
    @Autowired
    MerchandiseMapper merchandiseMapper;

    @Autowired
    RedisTemplate redisTemplate;

    public List<Merchandise> getAll(){
        return merchandiseMapper.getAllMerchandise();
    }

    public Merchandise getById(int id) { return merchandiseMapper.getMerchandiseById(id); }

    public void reduceStock(Merchandise merchandise){
        merchandiseMapper.reduceStock(merchandise);
    }

    public int getStock(Merchandise merchandise){
       return merchandiseMapper.getCount(merchandise.getMerchandise_id());
    }

    // redis task

    public void importToRedis(){
        redisTemplate.delete(Merchandise.class.getSimpleName());
        redisTemplate.delete(OrderInfo.class.getSimpleName());

        List<Merchandise> validMerchandise = new ArrayList<>();
        validMerchandise = merchandiseMapper.getValidMerchandise();

        for(Merchandise merchandise:validMerchandise){
            redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).put( Integer.toString(merchandise.getMerchandise_id()) , merchandise);
            //addQueue(merchandise.getMerchandise_id(), merchandise.getMerchandise_stock());
        }
    }

    private void addQueue(int id, int stockCount){
        if(stockCount >0){
            for(int i=0; i<stockCount; i++){
                redisTemplate.boundListOps(SysConfig.CONST_merchandise_pre+id).leftPush(id);
            }
        }
    }
}
