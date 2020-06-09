package com.learning.SecKillProject.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.learning.SecKillProject.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public String getToken(User user){
        String token ="";

        token = JWT.create()
                .withAudience(user.getUserId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));

        return token;
    }
}
