package com.example.backendsemesterapp.controller;


import com.example.backendsemesterapp.data.User;
import com.example.backendsemesterapp.dtos.LoginUserDTO;
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


    // Registering WITH RETURN OF JWT TOKEN

   /* @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(
            @RequestBody RegisterUserDTO request
    ) {
        return ResponseEntity.ok(userService.register(request));
    }*/

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterUserDTO request
    ) {
        return userService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponseDTO> authenticate(
            @RequestBody LoginUserDTO request
    ) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

}
