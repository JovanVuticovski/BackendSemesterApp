package com.example.backendsemesterapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/semester")
public class SemesterController {


    // Endpoint for Testing purpose only
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello Semester App");
    }
}