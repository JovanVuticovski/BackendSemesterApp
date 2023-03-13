package com.example.backendsemesterapp.repository;


import com.example.backendsemesterapp.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
    Optional<User> findByEmail(String email);  // Finding User by email(Unique)

    Optional<User> findByUserId(Integer Id); // Find by semester id
}
