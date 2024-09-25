package org.alten.tracing.handler.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.alten.tracing.handler.LoggingHandler;
import org.alten.tracing.util.LoggingUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DefaultLoggingHandler implements LoggingHandler {
    @Value("${logging.http.response.tracing-enabled}")
    private boolean traceHttpResponse;
    private Map<String, Long> startTimeMap = new HashMap<>();
    @Override
    public void handleBefore(JoinPoint joinPoint) {
        String methodFullName = this.getMethodFullName(joinPoint);
        long startTime = System.currentTimeMillis();
        startTimeMap.put(methodFullName, startTime);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            HttpServletRequest request = attributes.getRequest();

            log.info("Request: {} {} from {} | User-Agent: {} | Session ID: {} | Thread: {}",
                    request.getMethod(),
                    request.getRequestURI(),
                    request.getRemoteAddr(),
                    request.getHeader("User-Agent"),
                    request.getSession().getId(),
                    Thread.currentThread().getName()
            );

            LoggingUtil.logMethodStart(methodFullName, joinPoint.getArgs());
        }
    }

    @Override
    public void handleAfter(JoinPoint joinPoint) {
        String methodFullName = getMethodFullName(joinPoint);
        Long startTime = startTimeMap.remove(methodFullName);
        if (Objects.nonNull(startTime)) {
            long duration = System.currentTimeMillis() - startTime;
            LoggingUtil.logMethodEnd(methodFullName, duration);
        }
    }

    @Override
    public Object handleAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("------------------------------------------------------------------------------------------");
        Map<String, String> signature = getSignature(proceedingJoinPoint);
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(signature.get("returnType")).append(" ");
        signatureBuilder.append(signature.get("methodName"));
        signatureBuilder.append("(").append(signature.get("parameterNames")).append(")");
        log.info("A call to the API: {}", signatureBuilder);
        Object proceed = proceedingJoinPoint.proceed();
        log.info("------------------------------------------------------------------------------------------");
        return proceed;
    }

    @Override
    public void handleAfterReturning(JoinPoint joinPoint, Object result) {
        if (traceHttpResponse) {
            if (result instanceof ResponseEntity<?> responseEntity) {
                HttpStatusCode statusCode = responseEntity.getStatusCode();
                Object body = responseEntity.getBody();
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("statusCode", statusCode.value());
                responseMap.put("body", body);
                LoggingUtil.logReturningMethod(getMethodFullName(joinPoint), responseMap);
            } else {
                String logResult = null;

                if (result instanceof Optional<?> resultOptional) {
                    logResult = resultOptional.map(Object::toString).orElse(null);
                } else if (Objects.nonNull(result)) {
                    logResult = result.toString();
                }

                LoggingUtil.logReturningMethod(getMethodFullName(joinPoint), logResult);
            }
        }
    }

    @Override
    public void handleAfterThrowing(JoinPoint joinPoint, Throwable error) {
        LoggingUtil.logThrowingMethod(getMethodFullName(joinPoint),
                Objects.nonNull(error) ? error.getMessage() : null);
    }

    private Map<String, String> getSignature(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String returnType = signature.getReturnType().getName();
        String declaringTypeName = signature.getDeclaringTypeName();
        String name = signature.getMethod().getName();
        String parameterNames = Arrays.stream(signature.getParameterNames())
                .collect(Collectors.joining(", "));

        return Map.of("returnType", returnType,
                "methodName", declaringTypeName + "." + name,
                "parameterNames", parameterNames);
    }

    private String getMethodFullName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringTypeName() +
                "." +
                joinPoint.getSignature().getName();
    }
}
