package com.example.ozon.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
@Aspect
@Configuration
@Slf4j
public class LoggingAspect {

    @Pointcut("within(com.example.ozon.service..*)")
    public void serviceClassMethods() {
    }

    @Around("serviceClassMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object retval = joinPoint.proceed();
        long end = System.nanoTime();
        String methodName = joinPoint.getSignature().getName();
        log.info("Execution of " + methodName + " took " +
                TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retval;
    }


    @AfterThrowing(pointcut = "serviceClassMethods()",
            throwing = "ex")
    public void exceptionLogging(JoinPoint joinPoint, Exception ex) throws Throwable {
        log.error(ex.getMessage(), ex);
    }

    @Pointcut("within(com.example.ozon.controller.ControllerAdviser)")
    public void controllerAdviserMethods() {
    }

    @After("controllerAdviserMethods()")
    public void exceptionHandlerLoggind(JoinPoint joinPoint) {
        log.info("ExseptionHandler with name: {}", joinPoint.getSignature().getName());
    }


    @Pointcut("within(com.example.ozon.controller..*) && !within(*..ControllerAdviser)")
    public void controllerClassMethods() {
    }

    @After("controllerClassMethods()")
    public void controllerMethodsParameters(JoinPoint joinPoint) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Method with name: {}, from user with name: {} ", joinPoint.getSignature().getName(), auth);
    }


}
