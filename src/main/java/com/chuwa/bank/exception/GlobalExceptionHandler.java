package com.chuwa.bank.exception;

import com.chuwa.bank.exception.error.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails<String>> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                  WebRequest webRequest) {

        log.error("Resource is not found. {}", exception.getMessage());

        ErrorDetails<String> errorDetails = ErrorDetails.<String>builder()
              .timestamp(new Date())
              .message(exception.getMessage())
              .details(webRequest.getDescription(false))
              .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                                                   WebRequest webRequest) {

        Map<String, String> fieldErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((e) -> {
            String fieldName = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            fieldErrors.put(fieldName, message);
        });

        ErrorDetails<Map<String, String>> errorDetails = ErrorDetails.<Map<String, String>>builder()
              .timestamp(new Date())
              .message(fieldErrors)
              .details(webRequest.getDescription(false))
              .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails<String>> handleGlobalException(Exception exception,
                                                                        WebRequest webRequest) {


        ErrorDetails<String> errorDetails = ErrorDetails.<String>builder()
              .timestamp(new Date())
              .message(exception.getMessage())
              .details(webRequest.getDescription(false))
              .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
