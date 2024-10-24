package io.maksym.employeemanager.dto;

import lombok.*;

@Builder
@Getter
@Data
@AllArgsConstructor
public class ErrorDto {
    private String msg;

}
