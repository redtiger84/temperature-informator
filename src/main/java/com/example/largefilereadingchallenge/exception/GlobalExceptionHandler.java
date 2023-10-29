package com.example.largefilereadingchallenge.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataFileException.class)
    public ResponseEntity<Map<String, String>> handleDataFileException(DataFileException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "invalid_input_data");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
