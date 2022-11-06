package com.waa.lab2.aspect;

import com.waa.lab2.domain.Exception;
import com.waa.lab2.service.ExceptionService;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
public class ExceptionAspect {
    @Pointcut("execution(* com.waa.lab2.controller..*(..))")
    public void trackExceptions(){}

    @Autowired
    private ExceptionService exceptionService;

    @AfterThrowing(value = "trackExceptions()",throwing = "ex")
    public void logExceptionsToDatabase(Throwable ex){
        Exception exception=new Exception();
        exception.setExceptionType(ex.toString());
        exception.setOperation(ex.getMessage());
        exception.setTime(LocalTime.now());
        exception.setDate(LocalDate.now());
        exception.setPrincipal("Logged in User");
        exceptionService.save(exception);
    }
}
