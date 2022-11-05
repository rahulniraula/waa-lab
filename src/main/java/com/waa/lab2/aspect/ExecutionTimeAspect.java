package com.waa.lab2.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExecutionTimeAspect {
    @Pointcut("@annotation(com.waa.lab2.aspect.annotation.ExecutionTime)")
    public void executionTimeAnnotation(){}

    @Around("executionTimeAnnotation()")
    public Object calculateTotalExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startingTime=System.currentTimeMillis();
        var result=proceedingJoinPoint.proceed();
        long endingTime=System.currentTimeMillis();
        long total=(endingTime-startingTime);
        System.out.println(proceedingJoinPoint.getSignature().getName()+" takes "+total+" milliseconds");
        return result;

    }
}
