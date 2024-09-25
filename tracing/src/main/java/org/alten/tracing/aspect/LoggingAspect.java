package org.alten.tracing.aspect;

import lombok.extern.slf4j.Slf4j;
import org.alten.tracing.handler.LoggingHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    private final LoggingHandler loggingHandler;

    public LoggingAspect(LoggingHandler loggingHandler) {
        this.loggingHandler = loggingHandler;
    }

    @Pointcut("@annotation(org.alten.tracing.annotation.Loggable) || " +
              "@within(org.alten.tracing.annotation.Loggable)")
    private void controllerMethods() {
    }

    @Before("controllerMethods()")
    public void beforeControllerMethod(JoinPoint joinPoint) {
        loggingHandler.handleBefore(joinPoint);
    }

    @After("controllerMethods()")
    public void afterControllerMethod(JoinPoint joinPoint) {
        loggingHandler.handleAfter(joinPoint);
    }

    @Around("controllerMethods()")
    public Object aroundControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return loggingHandler.handleAround(proceedingJoinPoint);
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void afterReturningControllerMethod(JoinPoint joinPoint, Object result) {
        loggingHandler.handleAfterReturning(joinPoint, result);
    }

    @AfterThrowing(pointcut = "controllerMethods()", throwing = "error")
    public void afterThrowingControllerMethod(JoinPoint joinPoint, Throwable error) {
        loggingHandler.handleAfterThrowing(joinPoint, error);
    }

}
