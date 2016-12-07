package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class FaithfulAspect {
    @Pointcut("execution(* com.example.Routine..*(..))")
    public void allMethods() {}

    @Before("allMethods() && @annotation(Faithful)")
    public void beforeComtrollerMethodsLog(final JoinPoint joinPoint){
        System.out.println("Pray to God");
    }

    @After("allMethods() && @annotation(Faithful)")
    public void afterControllerMethodsLog(final JoinPoint joinPoint){
        System.out.println("Thank the God");
    }


}