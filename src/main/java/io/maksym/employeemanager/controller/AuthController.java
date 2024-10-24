//package io.maksym.employeemanager.controller;
//
//import io.maksym.employeemanager.dto.CredentialsDto;
//import io.maksym.employeemanager.dto.SignUpDto;
//import io.maksym.employeemanager.dto.UserDto;
//import io.maksym.employeemanager.security.UserAuthProvider;
//import io.maksym.employeemanager.service.UserService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.net.URI;
//
//@CrossOrigin("http://localhost:3000")
//@RequiredArgsConstructor
//@RestController
//public class AuthController {
//
//    private final UserService userService;
//    private final UserAuthProvider userAuthProvider;
//
//    @PostMapping("/login")
//    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto){
//        UserDto userDto = userService.login(credentialsDto);
//        userDto.setToken(userAuthProvider.createToken(userDto.getLogin()));
//        return ResponseEntity.ok(userDto);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto){
//        UserDto user = userService.register(signUpDto);
//        user.setToken(userAuthProvider.createToken(user.getLogin()));
//        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
//    }
//
//}
