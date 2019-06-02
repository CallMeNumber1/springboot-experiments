package com.example.reviewaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
@Aspect
public class MyAspect {
    @Around("execution(* com.example..*.*buy*(..))")
    public Object calculateExeTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime();
        log.debug("方法{}()的执行时间：{}", joinPoint.getSignature().getName(), end - start);
        return result;
    }
}
