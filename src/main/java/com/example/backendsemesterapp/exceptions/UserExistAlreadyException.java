package com.example.backendsemesterapp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "This User already exists")
public class UserExistAlreadyException extends Exception{
}
