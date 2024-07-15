package com.agendadeportistas.agendaservices.controllers;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.agendadeportistas.agendaservices.entities.ErrorMessage;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleException(DataIntegrityViolationException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getClass().getName(), ex.getMostSpecificCause().getMessage(), new Date());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getClass().getName(), ex.getMessage(), new Date());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
