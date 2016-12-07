package com.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimingAspect {
    @Pointcut("execution(* com.example.Main..*(..))")
    public void allVechiclerMethods(){}

    @Around(" allVechiclerMethods() && @annotation(Timed)")
    public Object aroundAllallVechiclerMethods(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        final long start_time=System.currentTimeMillis();
        try{
            final Object object_value=proceedingJoinPoint.proceed();
            return object_value;
        }catch(Throwable throwable){
            throw throwable;
        }finally{
            final long stop_time=System.currentTimeMillis();
            System.out.println("+++ Execution Time of "+proceedingJoinPoint.getSignature().getName()+" was : "+(stop_time-start_time));
        }
    }
}