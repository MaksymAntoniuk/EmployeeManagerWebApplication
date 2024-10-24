package io.maksym.employeemanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UsernameNotExistException extends RuntimeException{
    private final HttpStatus code;


    public UsernameNotExistException(String message, HttpStatus code){
        super(message);
        this.code = code;
    }

}
