package com.example.largefilereadingchallenge.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class DataFileException extends Exception {

    private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String MESSAGE = "invalid_input_data";

    public DataFileException() {
        super(MESSAGE);
    }
}
