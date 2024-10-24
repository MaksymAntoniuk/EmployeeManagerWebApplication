package io.maksym.employeemanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginAlreadyExistsException extends RuntimeException {
    private final HttpStatus code;
    public LoginAlreadyExistsException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
