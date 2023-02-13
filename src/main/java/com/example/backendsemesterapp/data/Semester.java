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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer semesterId;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String semesterType; // Make enum in future???
    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false, unique = true)
    private Integer phoneNumber;
    @Column()
    private String extraContactInfo;

    @ManyToOne // Multiple Semesters can belong to same User
    @JoinColumn(name = "appuser_id") // Join user column in database
    private User appuser; // Inject User from  entity class(User)

    public Semester(String firstname, String lastName, User appuser) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.appuser = appuser;
    }
}
