package com.learning.SecKillProject.model;

import java.io.Serializable;

public class User implements Serializable {
    private Integer user_id;
    private String nickname;
    private String password;
   // private Date lastLoginDate;

    public void setUserId(Integer user_id){
        this.user_id = user_id;
    }

    public void setNickname(String nickname){
        this.nickname=nickname;
    }

    public void setPassword(String password){
        this.password = password;
    }



    public Integer getUserId(){
        return user_id;
    }

    public String getNickname(){
        return nickname;
    }

    public String getPassword(){
        return password;
    }



}
