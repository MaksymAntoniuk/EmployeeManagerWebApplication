package io.maksym.employeemanager.mappers;

import io.maksym.employeemanager.dto.SignUpDto;
import io.maksym.employeemanager.model.User;
import io.maksym.employeemanager.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  UserMapper {

    UserDto toUserDto(User user);
@Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto userDto);
}
