package com.example.backendsemesterapp.controller;
import com.example.backendsemesterapp.data.Semester;
import com.example.backendsemesterapp.dtos.SemesterCreationDTO;
import com.example.backendsemesterapp.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/semester")
@RequiredArgsConstructor
public class SemesterController {


    private final SemesterService semesterService;
    // Get all semesters
    @GetMapping("/")
    public Collection<Semester> getAll() {
        return semesterService.getAll();
    }



    // Create Semester
    @PostMapping("/create")
    public Semester createSemester(@RequestBody SemesterCreationDTO semesterCreationDTO)

    {
        return semesterService.createSemester(
                semesterCreationDTO.getFirstName(),
                semesterCreationDTO.getLastName(),
                semesterCreationDTO.getSemesterType(),
                semesterCreationDTO.getStartDate(),
                semesterCreationDTO.getEndDate(),
                semesterCreationDTO.getPhoneNumber());



    }

}