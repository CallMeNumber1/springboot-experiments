package com.example.experiment04aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.OptionalInt;

@Component
@Aspect
@Slf4j
public class MyAspect {
    @Pointcut("execution(* com.example.experiment04aop.service..*.buy*(..))")
    public void pointcut() {}
    @Around("pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.nanoTime();
        // 要抛出异常？？
        Object object = joinPoint.proceed();
        long end = System.nanoTime();

        log.debug("执行时间: {}", end - start);
        return object;
    }
    // ！！！相当于和传进来的参数名称绑定
    @Around(value = "@within(myInterceptor) || @annotation(myInterceptor)")
    public Object interceptorTarget(ProceedingJoinPoint joinPoint, MyInterceptor myInterceptor) throws  Throwable {
        Optional.ofNullable(myInterceptor).or( () -> {
            MyInterceptor m = joinPoint.getTarget().getClass().getAnnotation(MyInterceptor.class);
            return Optional.of(m);
        }).ifPresent(m -> {
            for (MyInterceptor.AutorityType t : m.value()) {
                log.debug("{}", t);
            }
        });
        return joinPoint.proceed();
    }
}
