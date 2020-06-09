package com.learning.SecKillProject.model;

public class User {
    private Integer userId;
    private String nickname;
    private String password;
   // private Date lastLoginDate;

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public void setNickname(String nickname){
        this.nickname=nickname;
    }

    public void setPassword(String password){
        this.password = password;
    }



    public Integer getUserId(){
        return userId;
    }

    public String getNickname(){
        return nickname;
    }

    public String getPassword(){
        return password;
    }



}
