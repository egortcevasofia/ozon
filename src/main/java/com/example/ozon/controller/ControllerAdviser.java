package com.example.ozon.controller;

import com.example.ozon.exception.EmptyBucketException;
import com.example.ozon.exception.UserAlreadyExistsException;
import com.example.ozon.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdviser {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String notFoundExceptionHandler(UserNotFoundException ex) {
        return "User not found";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String userAlreadyExistsExceptionHandler(UserAlreadyExistsException ex) {
        return "User already exists";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyBucketException.class)
    public String EmptyBucketException(EmptyBucketException ex) {
        return "Your bucket is empty";
    }
}
