package com.example.libraryManagement.controller;

import com.example.libraryManagement.dto.LoginResponseDTO;
import com.example.libraryManagement.dto.RegisterRequestDTO;
import com.example.libraryManagement.entity.User;
import com.example.libraryManagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService ;

    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerUserDTO){
        return ResponseEntity.ok(authService.registerNormalUser(registerUserDTO)) ;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginResponseDTO loginResponseDTO){
        return ResponseEntity.ok(authService.login(loginResponseDTO)) ;
    }
}
