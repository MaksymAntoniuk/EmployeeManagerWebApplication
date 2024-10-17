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
    private double netAmount;

    @PrePersist
    @PreUpdate
    public void validateSalaryAndComputeNet() {
        if (amount <= 2000){
            throw new LowSalaryException("Salary can not be lower than 2000");
        }
        double annualSalary = amount * 12;
        if (annualSalary <= 120000){
            netAmount = amount - (((double) 12 /100) * amount);
        }
        if (annualSalary > 120000){
            netAmount = amount - (((double) 32 /100) * amount);
        }

    }

}
