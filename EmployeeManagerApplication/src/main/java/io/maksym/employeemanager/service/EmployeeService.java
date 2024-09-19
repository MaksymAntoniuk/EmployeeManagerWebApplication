package io.maksym.employeemanager.service;

import io.maksym.employeemanager.model.Employee;
import io.maksym.employeemanager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {
    List<Employee> getAllEmployee();

    Employee updateEmployee(Employee employee, long id);

    Employee createEmployee(Employee employee);

    Employee getEmployeeById(long id);

    void deleteEmployee(long id);

    boolean employeeExistById(long id);

}
