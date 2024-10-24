package io.maksym.employeemanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidPasswodException extends RuntimeException {
    private final HttpStatus code;

    public InvalidPasswodException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
