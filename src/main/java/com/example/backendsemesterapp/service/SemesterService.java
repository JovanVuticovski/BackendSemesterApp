package com.example.backendsemesterapp.service;

import com.example.backendsemesterapp.data.Semester;
import com.example.backendsemesterapp.exceptions.SemesterNotFoundException;
import com.example.backendsemesterapp.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository semesterRepository;

    public Collection<Semester> getAll() {
        return semesterRepository.findAll();
    }





                    // PROBLEM SOM MÅSTE FIXAS

    // Måste fixa så misslyckade requests inte triggar +1 på id i databas(returnar 403 vid samma email men id incrementeras)
    // Byta lösning då unique email skapar problem ifall samma användare vill skapa flera semestrar

    // Fixa så namnet på appuser visas när semester blir skapad
    // Göra så att endDate inte får vara tidigare än startDate
    // firstName och lastName får inte vara detsamma(extra)

    public Semester createSemester(String firstName, String lastName, String semesterType, String startDate, String endDate, Integer phoneNumber)
    //throws SemesterAlreadyExistsException
    {
        // var existing = semesterRepository.findByPhoneNumber(phoneNumber);
        //  if (existing.isPresent()) {
        //      log.info("Failed to create semester since name '" + phoneNumber + "' already exists.");
        //       throw new SemesterAlreadyExistsException();
        //  }

        var semester = new Semester(firstName, lastName,semesterType, startDate, endDate,phoneNumber);
        log.info("Successfully created  Semester with id '" + semester.getSemesterId() + "'.");
        return semesterRepository.save(semester);
    }




    public Semester deleteSemester(Integer id)
            throws SemesterNotFoundException

    {
        var optional = semesterRepository.findById(id);
        if (optional.isEmpty()) {
            log.info("Failed to delete semester since id '" + id + "' could not be found.");

            throw new SemesterNotFoundException();

        }

        var semester= optional.get();

        semesterRepository.delete(semester);
        log.info("Successfully deleted product with id '" + semester.getSemesterId() + "'.");

        return semester;
    }
}