package com.example.backendsemesterapp.data;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String contactInfo;
    @Column(nullable = false)
    private String semesterType;

    @Column(nullable = false)
    private String  startDate;
    @Column(nullable = false)
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    public Semester( String semesterType, String startDate, String endDate,  String contactInfo, String firstname, String lastname) {
        this.id = UUID.randomUUID();
        this.semesterType = semesterType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contactInfo = contactInfo;
        this.firstname = firstname;
        this.lastname = lastname;

        // this.creator = creator;
    }
}
