package io.maksym.employeemanager.service;


import io.maksym.employeemanager.exception.DuplicateEmployeeException;
import io.maksym.employeemanager.exception.EmployeeNotFoundException;
import io.maksym.employeemanager.model.Employee;
import io.maksym.employeemanager.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    public final EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        return employeeRepository.findById(id).map(empl ->{
          empl.setFirstName(employee.getFirstName());
          empl.setLastName(employee.getLastName());
          empl.setBirthDate(employee.getBirthDate());
          empl.setEmail(employee.getEmail());
          empl.setRole(employee.getRole());
          empl.setHireDate(employee.getHireDate());
          return employeeRepository.save(empl);
        }).orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + "not found"));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())){
            throw new DuplicateEmployeeException("Employee with email " + employee.getEmail() + "already exists");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {

        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));
    }

    @Override
    public void deleteEmployee(long id) {
        if (!employeeRepository.existsById(id)){
            throw new  EmployeeNotFoundException("Employee with id " + id + "not found");
        }

        employeeRepository.delete(employeeRepository.findById(id).orElseThrow(() ->  new  EmployeeNotFoundException("Employee with id " + id + " not found")));
    }

    @Override
    public boolean employeeExistById(long id) {
        return true;
    }
}
