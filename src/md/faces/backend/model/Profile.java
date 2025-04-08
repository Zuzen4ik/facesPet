package md.faces.backend.model;

import lombok.Data;

@Data
public class Profile {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String aboutMe;
    private Gender gender;

}
