package md.faces.backend.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Profile {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String aboutMe;
    private Gender gender;
    private LocalDate birthDate;

}
