package com.waa.lab2.aspect;

import com.waa.lab2.service.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Component
@Aspect
public class Logger {

    @Autowired
    private LoggerService loggerService;
    @Pointcut("execution(* com.waa.lab2.controller..*(..))")
    public void logAllEvents(){}
    @Before("logAllEvents()")
    public void log(JoinPoint joinPoint){
        com.waa.lab2.domain.Logger logObj=new com.waa.lab2.domain.Logger();
        logObj.setDate(LocalDate.now());
        logObj.setTime(LocalTime.now());
        logObj.setPrincipal("Current logged in user");
        logObj.setOperation(joinPoint.getSignature().toString());
        System.out.println(joinPoint.getSignature().toString());
        loggerService.logEvent(logObj);

    }
}
