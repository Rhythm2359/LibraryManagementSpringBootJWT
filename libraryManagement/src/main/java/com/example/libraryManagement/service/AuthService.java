package com.example.libraryManagement.service;

import com.example.libraryManagement.dto.LoginRequestDTO;
import com.example.libraryManagement.dto.LoginResponseDTO;
import com.example.libraryManagement.dto.RegisterRequestDTO;
import com.example.libraryManagement.entity.User;
import com.example.libraryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService; // ensure this bean exists in your project

    public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {

        if (userRepository.findByUserName(registerRequestDTO.getName()).isPresent()) {
            throw new RuntimeException("User already registered");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setName(registerRequestDTO.getName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {

        if (userRepository.findByUserName(registerRequestDTO.getName()).isPresent()) {
            throw new RuntimeException("User already registered");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setName(registerRequestDTO.getName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );

        User user = userRepository.findByUserName(loginRequestDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getName())
                .roles(user.getRoles())
                .build();
    }
}
