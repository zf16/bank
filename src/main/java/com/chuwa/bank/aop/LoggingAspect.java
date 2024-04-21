package com.chuwa.bank.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
//@Order(2)
public class LoggingAspect {

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Service *)" +
                " || within(@org.springframework.stereotype.Repository *) " +
                " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointCut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.chuwa.bank.controller..*)" +
          " || within(com.chuwa.bank.dao..*) " +
          " || within(com.chuwa.bank.controller..*)")
    public void applicationPackagePointCut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param ex        exception
     */
    @AfterThrowing(pointcut = "springBeanPointCut() && applicationPackagePointCut()", throwing = "ex")
    public void logAfterThrowingException(JoinPoint joinPoint, Throwable ex) {
        log.error("[AOP AfterThrowing] Exception in {}.{}() with cause = {}",
              joinPoint.getSignature().getDeclaringTypeName(),
              joinPoint.getSignature().getName(),
              ex.getCause() != null ? ex.getCause() : "NULL"
        );
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("springBeanPointCut() && applicationPackagePointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        log.debug("Enter: {}.{}() with argument(s) = {}",
              joinPoint.getSignature().getDeclaringTypeName(),
              joinPoint.getSignature().getName(),
              Arrays.toString(joinPoint.getArgs()));

        // ----- Before  ------

        try {
            Object result = joinPoint.proceed(); // joinPoint represents the method

            // ----- After ------
            log.debug("Exit: {}.{}() with result = {}",
                      joinPoint.getSignature().getDeclaringTypeName(),
                      joinPoint.getSignature().getName(),
                      result);

            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()",
                  Arrays.toString(joinPoint.getArgs()),
                  joinPoint.getSignature().getDeclaringTypeName(),
                  joinPoint.getSignature().getName()
            );
            throw e;
        }
    }
}
