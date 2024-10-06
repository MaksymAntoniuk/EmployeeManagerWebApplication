package io.maksym.employeemanager.repository;

import io.maksym.employeemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Boolean existsByEmail(String email);
    Boolean existsById(long id);
}
