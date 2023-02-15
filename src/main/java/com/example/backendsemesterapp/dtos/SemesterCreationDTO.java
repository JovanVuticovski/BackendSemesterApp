package com.example.backendsemesterapp.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class SemesterCreationDTO {
    private String firstName;
    private String lastName;
    private String semesterType;
    private String startDate;
    private String endDate;
    private Integer phoneNumber;


}
