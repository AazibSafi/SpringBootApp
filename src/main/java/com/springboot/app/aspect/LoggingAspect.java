package com.springboot.app.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger log;

    @Pointcut("execution(* com.springboot.app..*(..))")
    private void allWithinResource() {
    }

    @Pointcut("execution(* com.springboot.app.controller..*(..))")
    private void restApi() {
    }

    @Pointcut("execution(* com.springboot.app.repository..*(..))")
    private void database() {
    }

    @Pointcut("execution(* com.springboot.app.util..*(..))")
    private void util() {
    }

    @Before("allWithinResource() && !util()")
    public void before(JoinPoint joinPoint) {
        log = LogManager.getLogger(joinPoint.getSignature().getDeclaringType());
        log.info("<ENTRY>: {}", joinPoint.getSignature().getName());
    }

    @After("allWithinResource() && !util()")
    public void after(JoinPoint joinPoint) {
        log = LogManager.getLogger(joinPoint.getSignature().getDeclaringType());
        log.info("<EXIT>: {}", joinPoint.getSignature().getName());
    }

    @Around("restApi() || database()")
    public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log = LogManager.getLogger(joinPoint.getSignature().getDeclaringType());
        long start = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        String apiName = className + "." + methodName;
        Object result = joinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - start;
        log.info("<<<<<----- Execution Time: [{} ms] for [API: {}]", elapsedTime, apiName);
        return result;
    }

}
