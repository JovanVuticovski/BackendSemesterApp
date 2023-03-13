package com.example.backendsemesterapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A User with the specified id does not exists.")
public class UserNotFoundException extends Exception{
}
