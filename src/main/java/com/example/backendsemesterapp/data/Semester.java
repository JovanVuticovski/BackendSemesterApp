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
    private String semesterType;
    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false, unique = true)
    private Integer phoneNumber;
    @Column()
    private String extraContactInfo;

    @ManyToOne
    @JoinColumn(name = "appuser_id")
    private User appuser;

    public Semester(String firstname, String lastName, User appuser) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.appuser = appuser;
    }
}
