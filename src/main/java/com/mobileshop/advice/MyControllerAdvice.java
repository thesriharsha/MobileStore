package com.mobileshop.advice;

import com.mobileshop.custom.exception.IdNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    // Handling a User Defined method
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleIdNotFound(IdNotFoundException idNotFoundException)
    {
        return new ResponseEntity<String>(idNotFoundException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handling a system in-built method
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<Object>("Please change the http method type", HttpStatus.BAD_REQUEST);
    }
}
