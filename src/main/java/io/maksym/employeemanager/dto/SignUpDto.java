package io.maksym.employeemanager.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Builder
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String login;
    private char[] password;

}
