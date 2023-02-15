package com.example.backendsemesterapp.data;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Semester {

    @Id
    @SequenceGenerator(
            name = "semester_id_sequence",
            sequenceName = "semester_id_sequence",
            allocationSize = 1    // Incrementing id by 1 when adding new users to database

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "semester_id_sequence"
    )
    private Integer semesterId;


    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String semesterType; // Make enum in future???
    @Column(nullable = false) // make date objekt?
    private String startDate;

    @Column(nullable = false) // make date object?
    private String endDate;

    @Column(nullable = false, unique = true) // Unique causes same user to not be able to create multiple semesters
    private Integer phoneNumber;

  //  @Column()
   // private String extraContactInfo;

    @ManyToOne // Multiple Semesters can belong to same User
    @JoinColumn(name = "appuser_id") // Join user column in database
    private User appuser; // Inject User from  entity class(User)


    public Semester(String firstName, String lastName, String semesterType, String startDate, String endDate, Integer phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.semesterType = semesterType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.phoneNumber = phoneNumber;
    }
}
