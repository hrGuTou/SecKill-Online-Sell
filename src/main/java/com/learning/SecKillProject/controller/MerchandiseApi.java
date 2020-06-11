package com.learning.SecKillProject.controller;


import com.alibaba.fastjson.JSONObject;
import com.learning.SecKillProject.annotation.LoginRequired;
import com.learning.SecKillProject.model.Merchandise;
import com.learning.SecKillProject.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/merchandise")
@CrossOrigin(origins={"http://localhost:3000"})
public class MerchandiseApi {

    @Autowired
    MerchandiseService merchandiseService;

    @GetMapping("/all")
    public Object allMerchandises(){
        List<Merchandise> merchandise = merchandiseService.getAll();
        JSONObject res = new JSONObject();
        res.put("Merchandise", merchandise);
        return res;
    }
}
