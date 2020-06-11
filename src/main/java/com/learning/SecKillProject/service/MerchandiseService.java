package com.learning.SecKillProject.service;

import com.learning.SecKillProject.mapper.MerchandiseMapper;
import com.learning.SecKillProject.model.Merchandise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchandiseService {
    @Autowired
    MerchandiseMapper merchandiseMapper;

    public List<Merchandise> getAll(){
        return merchandiseMapper.getAllMerchandise();
    }

    public Merchandise getById(int id) { return merchandiseMapper.getMerchandiseById(id); }

    public void reduceStock(Merchandise merchandise){
        merchandiseMapper.reduceStock(merchandise);
    }
}
