package com.example.backendsemesterapp.service;

import com.example.backendsemesterapp.data.Role;
import com.example.backendsemesterapp.data.User;
import com.example.backendsemesterapp.dtos.LoginUserDTO;
import com.example.backendsemesterapp.dtos.RegisterUserDTO;
import com.example.backendsemesterapp.dtos.TokenResponseDTO;
import com.example.backendsemesterapp.jwtUtil.JwtTokenService;
import com.example.backendsemesterapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtService;
    private final AuthenticationManager authenticationManager;

/*
 // REGISTERING WITH JWT TOKEN  RETURN

    // Register User
    public TokenResponseDTO register(RegisterUserDTO request) {
        var user = User.builder() // Builder comes from annotation @Builder
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)

                .build();  // Registering User

        userRepository.save(user); // Adding User to database

        var jwtToken = jwtService.generateJwtToken(user);  // Generating JWT token to specified user
        return TokenResponseDTO.builder()
                .token(jwtToken)
                .build(); // Building response with Generated JWT token
    }

*/

    // Registering WITHOUT RETURN OF JWT TOKEN
    public User register(RegisterUserDTO request) {
        var user = User.builder() // Builder comes from annotation @Builder
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)

                .build();  // Registering User

        userRepository.save(user); // Adding User to database
        return user;
    }



    // Login User
    public TokenResponseDTO authenticate(LoginUserDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()) // Checks that provided email when authenticating exist in database
                .orElseThrow();
        var jwtToken = jwtService.generateJwtToken(user); // Generating JWT token to specified user
        return TokenResponseDTO.builder()
                .token(jwtToken)
                .build(); // Building response with Generated JWT token
    }
}
