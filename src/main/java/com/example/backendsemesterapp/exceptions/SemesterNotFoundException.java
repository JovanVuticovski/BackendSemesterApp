package com.example.backendsemesterapp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A Semester with the specified id does not exists.")
public class SemesterNotFoundException extends Exception{
}
