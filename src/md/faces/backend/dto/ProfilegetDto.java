package md.faces.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import md.faces.backend.model.Gender;

import java.time.LocalDate;

@Setter
@Getter
public class ProfilegetDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String aboutMe;
    private Gender gender;
    private LocalDate birthDate;
    private Integer age;

}
