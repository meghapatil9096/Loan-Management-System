package com.neosoft.aspect;

import com.neosoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

//    pointcut for all service methods
    @Pointcut("execution(* com.neosoft.service..*(..))")
    public void allServiceMethods() {}

//    Before advice
    @Before("allServiceMethods()")
    public void logBefore(JoinPoint joinPoint){
        log.info("Entering method: {} with args: {}",joinPoint.getSignature(),joinPoint.getArgs());
    }

//    After returning advice
    @AfterReturning(value = "allServiceMethods()",returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        log.info("Method {} returned: {}", joinPoint.getSignature(),result);
    }

//    After advice
    @After("allServiceMethods()")
    public void logAfter(JoinPoint joinPoint){
        log.info("Exiting method: {}", joinPoint.getSignature());
    }

//    After throwing advice
    @AfterThrowing("allServiceMethods()")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e){
        log.error("Method {} threw exception: {}", joinPoint.getSignature(), e.getMessage());
    }

}
