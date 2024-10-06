package io.maksym.employeemanager.model;

import io.maksym.employeemanager.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.nio.file.LinkOption;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @Column(unique = true)
    @NaturalId(mutable = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Roles role;

    private LocalDate hireDate;

    public Employee(String firstName, String lastName, LocalDate birthDate, String email, Roles role, LocalDate hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.role = role;
        this.hireDate = hireDate;
    }
}
