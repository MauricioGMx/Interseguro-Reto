package com.interseguro.proyect.soft.exception.v3.requestExceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionRequestHandler {

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> businessExceptionHandler(BusinessException businessException){
        return new ResponseEntity<>(businessException.getObjeto(), businessException.getHttpStatus());
    }

}
