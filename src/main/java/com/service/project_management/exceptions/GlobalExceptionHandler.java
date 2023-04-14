package com.service.project_management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.service.project_management.PayLoad.responseAPI;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String filedName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(filedName,message);
        });
        return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(resourceNotFoundException.class)
    public ResponseEntity<responseAPI> resourceNotFoundExceptionHandler(resourceNotFoundException ex){
        responseAPI responseAPI = new responseAPI(ex.getMessage(),false);
        return new ResponseEntity<>(responseAPI,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<responseAPI> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex){
        responseAPI responseAPI = new responseAPI("Please Gives Approprite Date formatt [ex: 2023-12-25]",false);
        return new ResponseEntity<>(responseAPI,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<responseAPI> SQLExceptionHandler(SQLException ex){
        responseAPI responseAPI = new responseAPI(ex.getMessage(),false);
        return new ResponseEntity<>(responseAPI,HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<responseAPI> ExceptionHandler(Exception ex){
        responseAPI responseAPI = new responseAPI(ex.getMessage(),false);
        return new ResponseEntity<>(responseAPI,HttpStatus.BAD_REQUEST);
    }    

}