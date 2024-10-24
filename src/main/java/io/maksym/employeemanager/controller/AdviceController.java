package io.maksym.employeemanager.controller;

import io.maksym.employeemanager.dto.ErrorDto;
import io.maksym.employeemanager.exception.*;
import io.maksym.employeemanager.rest.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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

//    @ExceptionHandler(value = {InvalidPasswodException.class})
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorDto> invalidPasswordException(InvalidPasswodException ex){
        return ResponseEntity.status(ex.getCode()).body(ErrorDto.builder().msg(ex.getMessage()).build());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorDto> loginAlreadyExistsException(LoginAlreadyExistsException ex){
        return ResponseEntity.status(ex.getCode()).body(ErrorDto.builder().msg(ex.getMessage()).build());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorDto> usernameNotExistException(UsernameNotExistException ex){
        return ResponseEntity.status(ex.getCode()).body(ErrorDto.builder().msg(ex.getMessage()).build());
    }



}
