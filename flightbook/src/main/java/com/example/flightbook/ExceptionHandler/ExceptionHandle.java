package com.example.flightbook.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.mail.AuthenticationFailedException;

@RestControllerAdvice
public class ExceptionHandle {
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
      return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }


     @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<String> handleMailAuthException(AuthenticationFailedException ex) {
        return new ResponseEntity<>(
                "Email sending failed: Invalid Gmail credentials or App Password issue",
                HttpStatus.UNAUTHORIZED
        );
    }

       @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(
                "Something went wrong: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
