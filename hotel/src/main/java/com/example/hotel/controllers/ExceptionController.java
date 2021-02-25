package com.example.hotel.controllers;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception controller class
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Returns error page in case of an exception
     *
     * @return error page
     */
    @ExceptionHandler(value = Exception.class)
    public String handleError() {
        return "error";
    }


}
