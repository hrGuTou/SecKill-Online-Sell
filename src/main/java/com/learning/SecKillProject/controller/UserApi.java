package com.learning.SecKillProject.controller;


import com.alibaba.fastjson.JSONObject;
import com.learning.SecKillProject.annotation.CurrentUser;
import com.learning.SecKillProject.annotation.LoginRequired;
import com.learning.SecKillProject.model.User;
import com.learning.SecKillProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserApi {
    @Autowired
    UserService userService;

    @PostMapping("/register") //post method api/user/register
    public Object add(@RequestBody User user){
        //if user exist, return false
        if(userService.findByUserId(user.getUserId())!=null){
            JSONObject res = new JSONObject();
            res.put("error","Username already exist!");
            return res;

        }
        userService.add(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }





}
