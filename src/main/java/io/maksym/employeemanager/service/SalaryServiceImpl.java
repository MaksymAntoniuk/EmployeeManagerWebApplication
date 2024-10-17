package io.maksym.employeemanager.service;

import io.maksym.employeemanager.exception.EmployeeNotFoundException;
import io.maksym.employeemanager.model.Employee;
import io.maksym.employeemanager.model.Salary;
import io.maksym.employeemanager.repository.EmployeeRepository;
import io.maksym.employeemanager.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService{
    @Autowired
    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Salary addSalary(long employeeId, double salaryAmount) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new EmployeeNotFoundException("Employee with id " + " not found")
        );

        Salary salary = new Salary();
        salary.setAmount(salaryAmount);
        salary.setEmployee(employee);
        salaryRepository.save(salary);
        return salaryRepository.save(salary);
    }

    @Override
    public Salary updateSalary(Employee employee, double salaryAmount) {
        Salary salary = salaryRepository.findByEmployee(employee).orElseThrow(() -> new EmployeeNotFoundException("Salary for Employee with id" + employee.getId() + " not found") );
        salary.setAmount(salaryAmount);
        salaryRepository.save(salary);
        return salaryRepository.save(salary);
    }

    @Override
    public Salary getSalaryById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee witt id " + id + " not found"));
        return salaryRepository.findByEmployee(employee).orElseThrow(null);
    }

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public void deleteSalary(long id) {
        salaryRepository.deleteById(id);
    }


}
