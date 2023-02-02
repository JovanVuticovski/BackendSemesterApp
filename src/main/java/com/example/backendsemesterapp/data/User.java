package com.example.backendsemesterapp.data;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;


@Entity(name = "creator")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "creator")
    @JsonIgnoreProperties("creator")
    private Collection<Semester> semesters;

    public User(String username, String email, String password, Role role, Collection<Semester> semesters) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.semesters = semesters;
    }
}