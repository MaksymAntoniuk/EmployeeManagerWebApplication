package io.maksym.employeemanager.service;

import io.maksym.employeemanager.model.Employee;
import io.maksym.employeemanager.model.Salary;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalaryService {
    Salary addSalary(long employeeId, double salaryAmount);
    Salary updateSalary(Employee employee, double salaryAmount);
    Salary getSalaryById(long id);
    List<Salary> getAllSalaries();
    void deleteSalary(long id);
}
