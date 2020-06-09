package com.learning.SecKillProject.mapper;


import com.learning.SecKillProject.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO sk_user(userId, nickname, password) VALUES (#{userId}, #{nickname}, #{password})")
    void add(User user);

    @Select("select * from sk_user where userId = #{userId}")
    User getById(@Param("userId")int userId);

}
