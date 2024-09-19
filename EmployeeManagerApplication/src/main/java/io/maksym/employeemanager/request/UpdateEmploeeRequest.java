package io.maksym.employeemanager.request;


import io.maksym.employeemanager.enums.Roles;
import io.maksym.employeemanager.model.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateEmploeeRequest {
    @Pattern(regexp ="^[a-zA-Z]+$", message = "First name must contain only letters" )
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @Pattern(regexp ="^[a-zA-Z]+$", message = "Last name must contain only letters" )
    private String lastName;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Pattern(regexp = "IT_TECHNICIAN|SUPPORT_SPECIALIST|WEB_DEVELOPER|SYSTEM_ANALYST|SOFTWARE_ENGINEER|DATABASE_ADMINISTRATOR|IT_DIRECTOR|USER_EXPERIENCE_DESIGNER",
            message = "Role must be IT_TECHNICIAN, SUPPORT_SPECIALIST, WEB_DEVELOPER, SYSTEM_ANALYST, SOFTWARE_ENGINEER, DATABASE_ADMINISTRATOR, IT_DIRECTOR, USER_EXPERIENCE_DESIGNER")
    private String role;

    private LocalDate hireDate;

    public Employee toEmployee(){
        return Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .email(email)
                .role(Roles.valueOf(role))
                .hireDate(hireDate)
                .build();
    }
}
