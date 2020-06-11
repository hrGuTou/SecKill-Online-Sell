package com.learning.SecKillProject.mapper;


import com.learning.SecKillProject.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(user_id, nickname, password) VALUES (#{user_id}, #{nickname}, #{password})")
    void add(User user);

    @Select("select * from user where user_id = #{user_id}")
    User getById(@Param("user_id")int user_id);

}
