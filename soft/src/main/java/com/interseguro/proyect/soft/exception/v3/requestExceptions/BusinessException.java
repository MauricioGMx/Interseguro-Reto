package com.interseguro.proyect.soft.exception.v3.requestExceptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessException extends RuntimeException{

    private Object objeto;
    private HttpStatus httpStatus;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Object objeto, HttpStatus httpStatus){
        this.objeto = objeto;
        this.httpStatus = httpStatus;
    }


    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
