package com.learning.SecKillProject.config;


import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;

@ControllerAdvice
public class ExceptionHandlerGlobal {
    //private Logger logger = LoggerFactory.getLogger(ExceptionHandlerGlobal.class);
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        //logger.error(ExceptionUtils.getFullStackTrace(e));  // 记录错误信息
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "Server error";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", msg);
        return jsonObject;
    }
}
