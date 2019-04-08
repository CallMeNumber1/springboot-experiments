package com.example.experiment04aop.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface MyInterceptor {
    // 细粒度的权限划分，因为一个用户可能具有多个权限
    AutorityType[] value() default AutorityType.USER;
    public static enum AutorityType {
        USER, ADMIN, SUPERADMIN
    }
}
