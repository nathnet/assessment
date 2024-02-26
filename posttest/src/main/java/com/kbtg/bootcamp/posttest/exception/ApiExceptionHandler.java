package com.kbtg.bootcamp.posttest.exception;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler({ BadRequestException.class })
  public ResponseEntity<ApiErrorResponse> BadRequestException(BadRequestException e) {
    ApiErrorResponse errorResponse = new ApiErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ MethodArgumentNotValidException.class })
  public ResponseEntity<ApiErrorResponse> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
    List<String> errors = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    
    String errorString = String.join(", ", errors);

    ApiErrorResponse errorResponse = new ApiErrorResponse(errorString, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
