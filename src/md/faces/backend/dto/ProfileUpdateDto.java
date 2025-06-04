package md.faces.backend.dto;

import lombok.Getter;
import lombok.Setter;
import md.faces.backend.model.Gender;

import java.time.LocalDate;

@Getter
@Setter
public class ProfileUpdateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String aboutMe;
    private Gender gender;
    private LocalDate birthDate;
}
