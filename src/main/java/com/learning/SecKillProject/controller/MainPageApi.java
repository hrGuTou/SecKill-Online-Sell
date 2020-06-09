package com.learning.SecKillProject.controller;

import com.learning.SecKillProject.annotation.CurrentUser;
import com.learning.SecKillProject.annotation.LoginRequired;
import com.learning.SecKillProject.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/site")
public class MainPageApi {

    @LoginRequired
    @GetMapping("/main")
    public Object main(@CurrentUser User user) {
        return user.getNickname();
    }
}
