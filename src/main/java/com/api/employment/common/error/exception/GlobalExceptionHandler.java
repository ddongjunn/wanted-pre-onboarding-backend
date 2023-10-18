package com.api.employment.common.error.exception;

import com.api.employment.common.domain.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> dtoValidation(MethodArgumentNotValidException ex){
        String msg = "[ " + ex.getBindingResult().getFieldError().getField() + " ] " + ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponse(msg));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex){
        String msg = "parameter is null : " + ex.getParameterName();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponse(msg));
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<?> handleCustomException(CustomException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponse(ex.getErrorCode().getMessage()));
    }

    private ResponseMessage createResponse(String message) {
        return new ResponseMessage(message);
    }

}
