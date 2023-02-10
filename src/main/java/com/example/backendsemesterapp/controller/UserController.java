package com.example.backendsemesterapp.controller;


import com.example.backendsemesterapp.dtos.LoginRequestDTO;
import com.example.backendsemesterapp.dtos.RegisterUserDTO;
import com.example.backendsemesterapp.dtos.TokenResponseDTO;
import com.example.backendsemesterapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(
            @RequestBody RegisterUserDTO request
    ) {
        return ResponseEntity.ok(userService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponseDTO> authenticate(
            @RequestBody LoginRequestDTO request
    ) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

}
