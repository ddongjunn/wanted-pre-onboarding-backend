package com.api.employment.common.logging;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;


@Component
@Aspect
@Slf4j
public class LoggingAspect {

    private final ObjectMapper mapper = new ObjectMapper();

    @Pointcut("@within(com.api.employment.common.logging.LoggableController)")
    private void loggableControllers(){}

    @Around("loggableControllers()")
    public Object loggingControllers(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //request
        log.info("request: {} {}", request.getMethod(), request.getRequestURI());
        log.info("parameters: {}", mapper.writeValueAsString(params(joinPoint))); //writerWithDefaultPrettyPrinter()

        //response
        Object response = joinPoint.proceed(joinPoint.getArgs());
        ResponseEntity<?> responseEntity = (ResponseEntity<?>) response;
        log.info("response: {}", mapper.writeValueAsString(responseEntity.getBody()));
        return response;
    }

    @AfterThrowing(pointcut = "loggableControllers()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("error: {}", e.toString());
    }

    private Map params(JoinPoint joinPoint){
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        Map<String, Object> params = new HashMap<>();
        for(int i = 0; i < parameterNames.length; i++){
            params.put(parameterNames[i], args[i]);
        }
        return params;
    }
}
