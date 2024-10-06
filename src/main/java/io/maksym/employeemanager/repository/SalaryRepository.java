package io.maksym.employeemanager.repository;

import io.maksym.employeemanager.model.Employee;
import io.maksym.employeemanager.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    Optional<Salary> findByEmployee(Employee employee);
}
