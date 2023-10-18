package com.api.employment.common.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@Aspect
@Slf4j
public class LoggingAspect {

    private final ObjectMapper mapper = new ObjectMapper();

    @Pointcut("@within(com.api.employment.common.logging.LoggableController)")
    private void loggableControllers(){}

    @Before("loggableControllers()")
    public void before(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("request: {} {} parameter: {}",request.getMethod(), request.getRequestURI(), joinPoint.getArgs().toString());
    }

    @AfterReturning(value = "loggableControllers()", returning = "returnObj")
    public void after(JoinPoint joinPoint, Object returnObj) {
        log.info("response: {}", returnObj.toString());
    }

    @AfterThrowing(pointcut = "loggableControllers()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("error {}", e.getMessage());
        log.error("Exception at {} with cause = {}", joinPoint.getSignature().toShortString(), e.getCause() != null? e.getCause(): "NULL");
    }
}
