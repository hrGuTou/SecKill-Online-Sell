package com.learning.SecKillProject.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.learning.SecKillProject.annotation.LoginRequired;
import com.learning.SecKillProject.model.User;
import com.learning.SecKillProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    /*
    * HandlerInterceptor 定义了三个方法
    *
    * boolean preHandle():
    *   return
    *       true 继续流程
    *       false 中断
    *
    * void postHandle():
    *   DispatcherServlet 进行视图返回渲染之前进行调用
    *   此时可以通过modelAndView对模型数据或视图进行处理
    *   可能为null
    *
    * void afterCompletion():
    *   用于进行资源清理
    *   在 DispatcherServlet 渲染了对应的视图之后执行
    * */

    /*
    *
    * 主要流程:
        1. 从 http 请求头中取出 token，
        2. 判断是否映射到方法
        3. 检查是否有 passtoken 注释，有则跳过认证
        4. 检查有没有需要用户登录的注解，有则需要取出并验证
        5. 认证通过则可以访问，不通过会报相关错误信息
    *
    * */

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //是否需要login @LoginRequired
        LoginRequired annoMethod = method.getAnnotation(LoginRequired.class);
        if(annoMethod != null) {
            //需要login
            String token = request.getHeader("token");
            if(token == null){
                throw new RuntimeException("No token");
                //need to log in
            }

            int userId;
            try{
                userId = Integer.parseInt(JWT.decode(token).getAudience().get(0)); //get userId from token
            }catch(JWTDecodeException e){
                //userId not valid
                throw new RuntimeException("Invalid token");
            }

            User user = userService.findByUserId(userId);

            if(user == null){
                //no such user
                throw new RuntimeException("No such user");
            }

            //validating
            JWTVerifier verifier =  JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            try {
                verifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("Invalid token");
            }

            request.setAttribute("currentUser", user);
            return true;
        }

        return true;
    }


}
