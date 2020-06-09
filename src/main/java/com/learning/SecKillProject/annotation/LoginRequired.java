package com.learning.SecKillProject.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义annotation
//凡是有@LoginRequired都需要都需要验证

@Target({ElementType.METHOD}) //作用于方法
@Retention(RetentionPolicy.RUNTIME) //运行时起作用
public @interface LoginRequired {
}
