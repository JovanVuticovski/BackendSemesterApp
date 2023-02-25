package com.example.backendsemesterapp.dtos;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private String email;

    @NonNull
    private String password;
}
