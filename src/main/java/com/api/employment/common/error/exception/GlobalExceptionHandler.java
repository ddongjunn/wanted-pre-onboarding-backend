package com.api.employment.common.error.exception;

import com.api.employment.common.domain.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> dtoValidation(MethodArgumentNotValidException ex){
        String msg = "[ " + ex.getBindingResult().getFieldError().getField() + " ] " + ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorMessage errorMessage = new ErrorMessage(msg);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorMessage> handleMaterialMasterException(CustomException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getErrorCode().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
