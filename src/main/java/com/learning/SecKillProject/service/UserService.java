package com.learning.SecKillProject.service;

import com.learning.SecKillProject.mapper.UserMapper;
import com.learning.SecKillProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    private String passwordToHash(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            byte[] src = digest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            // 字节数组转16进制字符串
            // https://my.oschina.net/u/347386/blog/182717
            for (byte aSrc : src) {
                String s = Integer.toHexString(aSrc & 0xFF);
                if (s.length() < 2) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return null;
    }

    public boolean add(User user){
        String hashPass = passwordToHash(user.getPassword());
        user.setPassword(hashPass);
        userMapper.add(user);
        return true;
    }

    public User findByUserId(int id){
        return userMapper.getById(id);
    }

    public boolean comparePass(User login, User usrDB){
        return passwordToHash(login.getPassword()).equals(usrDB.getPassword());
    }

}
