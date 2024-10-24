package io.maksym.employeemanager.service;

import io.maksym.employeemanager.dto.CredentialsDto;
import io.maksym.employeemanager.dto.SignUpDto;
import io.maksym.employeemanager.exception.InvalidPasswodException;
import io.maksym.employeemanager.exception.LoginAlreadyExistsException;
import io.maksym.employeemanager.exception.UsernameNotExistException;
import io.maksym.employeemanager.mappers.UserMapper;
import io.maksym.employeemanager.model.User;
import io.maksym.employeemanager.dto.UserDto;
import io.maksym.employeemanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserDto findByLogin(String login){
        User user = userRepository.findByLogin(login)
                .orElseThrow(()-> new UsernameNotExistException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto){
        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new UsernameNotExistException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()),user.getPassword())){
            return userMapper.toUserDto(user);
        }
        throw new InvalidPasswodException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto){
        Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());

        if (optionalUser.isPresent()){
            throw new LoginAlreadyExistsException("Login already exists", HttpStatus.BAD_REQUEST);
        }
        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }


}
