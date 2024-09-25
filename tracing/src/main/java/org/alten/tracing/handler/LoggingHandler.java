package org.alten.tracing.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public interface LoggingHandler {
    void handleBefore(JoinPoint joinPoint);

    void handleAfter(JoinPoint joinPoint);

    Object handleAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable;

    void handleAfterReturning(JoinPoint joinPoint, Object result);

    void handleAfterThrowing(JoinPoint joinPoint, Throwable error);
}
