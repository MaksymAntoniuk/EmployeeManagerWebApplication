package io.maksym.employeemanager.exception;

public class LowSalaryException extends RuntimeException {
    public LowSalaryException(String message) {
        super(message);
    }
}
