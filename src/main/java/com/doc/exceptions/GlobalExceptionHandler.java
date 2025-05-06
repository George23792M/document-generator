package com.doc.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {


        //Extract Error Messages
        List<FieldErrorDto> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        log.error("Input validation failed: {}", errors, ex);

        //Construct Error Custom Response
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Input Validation Failed", errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedDocumentTypeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFileTypeException(UnsupportedDocumentTypeException ex) {

        log.error("Invalid File Type provided: {}", ex.getMessage(), ex);

        //Construct Error Custom Response
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);


    }


}
