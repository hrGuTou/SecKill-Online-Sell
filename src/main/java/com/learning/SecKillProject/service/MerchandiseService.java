package com.learning.SecKillProject.service;

import com.learning.SecKillProject.mapper.MerchandiseMapper;
import com.learning.SecKillProject.model.Merchandise;
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
        List<Merchandise> validMerchandise = new ArrayList<>();
        validMerchandise = merchandiseMapper.getValidMerchandise();

        for(Merchandise merchandise:validMerchandise){
            redisTemplate.boundHashOps(Merchandise.class.getSimpleName()).put( Integer.toString(merchandise.getMerchandise_id()) , merchandise);
        }
    }
}
