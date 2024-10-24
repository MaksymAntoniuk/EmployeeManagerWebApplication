package io.maksym.employeemanager.controller;

import io.maksym.employeemanager.dto.CredentialsDto;
import io.maksym.employeemanager.dto.SignUpDto;
import io.maksym.employeemanager.dto.UserDto;
import io.maksym.employeemanager.exception.ValidationException;
import io.maksym.employeemanager.model.Employee;
import io.maksym.employeemanager.model.Salary;
import io.maksym.employeemanager.request.CreateEmployeeRequest;
import io.maksym.employeemanager.request.UpdateEmploeeRequest;
import io.maksym.employeemanager.security.UserAuthProvider;
import io.maksym.employeemanager.service.EmployeeService;
import io.maksym.employeemanager.service.SalaryService;
import io.maksym.employeemanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final SalaryService salaryService;

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto){
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthProvider.createToken(userDto.getLogin()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto){
        UserDto user = userService.register(signUpDto);
        user.setToken(userAuthProvider.createToken(user.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }

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
//        Employee createEmployee = employeeService.createEmployee(employeeRequest.toEmployee(),employeeRequest.getSalaryAmount());
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


    @GetMapping("/salary")
    public ResponseEntity<List<Salary>> getSalaryEmployee(){
        List<Salary> obtain = salaryService.getAllSalaries();
        return ResponseEntity.ok(obtain);
    }

    @GetMapping("employee/salary/{id}")
    public ResponseEntity<Double> getSalaryByEmployeeId(@PathVariable long id){
        Salary salary = salaryService.getSalaryById(id);
        return ResponseEntity.ok(salary.getAmount());
    }

    @DeleteMapping("/salary/delete/{id}")
    public void deleteSalaryEmployee(@PathVariable long id){
        salaryService.deleteSalary(id);
    }

    @PostMapping("/employee/add/salary/{id}")
    public ResponseEntity<Salary> addSalary(@PathVariable long id, @RequestParam double amount){
        Salary addSalary = salaryService.addSalary(id, amount);
        return ResponseEntity.status(HttpStatus.OK).body(addSalary);
    }

    @PutMapping("/employee/update/salary/{id}")
    public ResponseEntity<Salary> updateSalary(@PathVariable long id, @RequestParam double amount){
        Employee employee = employeeService.getEmployeeById(id);
        Salary updateSalary = salaryService.updateSalary(employee, amount);
        return ResponseEntity.ok(updateSalary);
    }




}


