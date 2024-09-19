package io.maksym.employeemanager.controller;

import io.maksym.employeemanager.exception.EmployeeNotFoundException;
import io.maksym.employeemanager.exception.ValidationException;
import io.maksym.employeemanager.model.Employee;
import io.maksym.employeemanager.request.CreateEmployeeRequest;
import io.maksym.employeemanager.request.UpdateEmploeeRequest;
import io.maksym.employeemanager.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> obtainEmployees = employeeService.getAllEmployee();
        return ResponseEntity.ok(obtainEmployees);
//        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public  ResponseEntity<Employee> createEmployee(@RequestBody @Valid CreateEmployeeRequest employeeRequest,
                                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().stream().map(err ->
                    err.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new ValidationException(message);
        }
        Employee createEmployee = employeeService.createEmployee(employeeRequest.toEmployee());
        return ResponseEntity.status(HttpStatus.CREATED).body(createEmployee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody @Valid UpdateEmploeeRequest emploeeRequest,
                                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String message = bindingResult.getAllErrors().stream().map(err ->
                    err.getDefaultMessage()
            ).collect(Collectors.joining(", "));
            throw new ValidationException(message);
        }
        Employee updateEmployee = employeeService.updateEmployee(emploeeRequest.toEmployee(),id);
        return ResponseEntity.ok(updateEmployee);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
            Employee obtainEmployee = employeeService.getEmployeeById(id);
            return ResponseEntity.status(HttpStatus.OK).body(obtainEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") long id){
            employeeService.deleteEmployee(id);
    }
}
