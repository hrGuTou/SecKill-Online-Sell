package com.learning.SecKillProject.controller;


import com.alibaba.fastjson.JSONObject;
import com.learning.SecKillProject.model.User;
import com.learning.SecKillProject.service.AuthenticationService;
import com.learning.SecKillProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authentication")
@CrossOrigin(origins={"http://localhost:3000"})

public class AuthenticationApi {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @PostMapping("/login") //POST method api/authentication/login
    public Object login(@RequestBody User user){
        User usrDB = userService.findByUserId(user.getUserId());
        JSONObject res = new JSONObject();
        if(usrDB == null){
            res.put("error","User doesn't exist");
        }else if(!userService.comparePass(user, usrDB)){
            res.put("error", "Incorrect password");
        }else{
            //generate token
            String token = authenticationService.getToken(usrDB);
            res.put("token", token);
            res.put("userNickname", usrDB.getNickname());
        }
        return res;
    }

}
