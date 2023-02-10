package com.example.backendsemesterapp.data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "appuser")

public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "app_user_id_sequence",
            sequenceName = "app_user_id_sequence",
            allocationSize = 1    // Incrementing id by 1 when adding new users to database

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_id_sequence"
    )

    private Integer id;
    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    // Hides password from displaying in body
    // @JsonProperty(access = READ_ONLY)
    private String password;


    @Column(nullable = false,unique = true)  // Same email twice gives 403 forbidden status code
    private String email;


    @Enumerated(EnumType.STRING)  // EnumType.String to define Roles by String(text) input
    private Role role; //Defining enum Class

    @OneToMany(mappedBy = "appuser")
    @JsonIgnoreProperties("appuser")
    private Collection<Semester> semesters;


    // Returns a list of Roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(role.name())); //Role.name referencing Role enum variable
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {  // Email = username in the application
        return email ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
