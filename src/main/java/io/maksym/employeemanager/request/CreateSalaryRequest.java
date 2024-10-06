package io.maksym.employeemanager.request;

import io.maksym.employeemanager.model.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSalaryRequest {
    private double amount;
    private Employee employee;




}
