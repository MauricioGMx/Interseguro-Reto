package com.interseguro.proyect.soft.exception.v2.advice;
import com.interseguro.proyect.soft.exception.v2.requestExceptions.NotFoundRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(value = {NotFoundRException.class})
    public ResponseEntity<Object> handleEmptyInput(NotFoundRException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
