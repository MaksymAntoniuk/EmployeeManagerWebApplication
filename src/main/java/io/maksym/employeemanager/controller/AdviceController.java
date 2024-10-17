package io.maksym.employeemanager.controller;

import io.maksym.employeemanager.exception.DuplicateEmployeeException;
import io.maksym.employeemanager.exception.EmployeeNotFoundException;
import io.maksym.employeemanager.exception.ValidationException;
import io.maksym.employeemanager.rest.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> notFoundEmployeeException(EmployeeNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(ex.getMessage())
                        .build());

    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> validationException(ValidationException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder().message(ex.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> duplicateEmployeeException(DuplicateEmployeeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder().message(ex.getMessage()).build());
    }


}
