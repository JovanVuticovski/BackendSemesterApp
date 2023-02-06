package com.example.backendsemesterapp.repository;

import com.example.backendsemesterapp.data.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {


    // Can be adjusted to get by id
    Optional<Semester> getBySemesterId(Integer semesterId);
}
