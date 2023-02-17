package com.example.backendsemesterapp.controller;
import com.example.backendsemesterapp.data.Semester;
import com.example.backendsemesterapp.dtos.SemesterCreationDTO;
import com.example.backendsemesterapp.exceptions.SemesterNotFoundException;
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



    // Get single/one Semester
    @GetMapping("/{id}")
    public Semester getSemesterById(@PathVariable String id) // PathVariable accept only string values
            throws SemesterNotFoundException
    {

        return semesterService.getSemesterById(Integer.valueOf((id))); // cast string value to integer
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



    //Delete Semester by id
    // Fix bugg where id keeps incrementing after deleting semester

    @DeleteMapping("/{id}")
    public Semester deleteSemester(@PathVariable String id) // PathVariable accept only String format
            throws SemesterNotFoundException
    {

        return semesterService.deleteSemester(Integer.valueOf((id))); // casting String value to Integer
    }

}