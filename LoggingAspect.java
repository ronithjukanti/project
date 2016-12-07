package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.Main..*(..))")
    public void addVechileControllerLoggingInfo(final JoinPoint joinPoint) {
        final long start_time = System.currentTimeMillis();
        System.out.println("+++ Each time this method is called " + start_time);
        System.out.println("*** Executing: " + joinPoint.getSignature());
        Object[] passed_aguments = joinPoint.getArgs();
        if (passed_aguments != null && passed_aguments.length != 0) {
            for (Object argument : passed_aguments) {
                System.out.println("*** " + argument.getClass().getSimpleName() + " = " + argument);
            }
        }
        System.out.println("**** Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }
}
