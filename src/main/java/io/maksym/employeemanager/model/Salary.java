package io.maksym.employeemanager.model;

import io.maksym.employeemanager.exception.LowSalaryException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;
    private double amount;

    @PrePersist
    @PreUpdate
    public void validateSalaryAmount() {
        if (amount <= 2000){
            throw new LowSalaryException("Salary can not be lower than 2000");
        }
    }
}
