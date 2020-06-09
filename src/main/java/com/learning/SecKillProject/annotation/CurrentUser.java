package com.learning.SecKillProject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER}) //作用范围在方法参数
@Retention(RetentionPolicy.RUNTIME) //在运行时有效
public @interface CurrentUser {

}
