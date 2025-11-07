package com.example.libraryManagement.controller;


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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthService authService ;

    @PostMapping("/registeradminuser")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        return ResponseEntity.ok(authService.registerAdminUser(registerRequestDTO));
    }
}
