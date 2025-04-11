package md.faces.backend.mapper;

import md.faces.backend.dto.ProfilegetDto;
import md.faces.backend.model.Profile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProfileToDtoMapper implements Mapper<Profile, ProfilegetDto>{

    private static final ProfileToDtoMapper INSTANCE = new ProfileToDtoMapper();

    private ProfileToDtoMapper() {}

    public static ProfileToDtoMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public ProfilegetDto from(Profile obj) {
        return from(obj, new ProfilegetDto());
    }

    @Override
    public ProfilegetDto from(Profile obj, ProfilegetDto dto) {
        dto.setId(obj.getId());
        dto.setFirstName(obj.getFirstName());
        dto.setLastName(obj.getLastName());
        dto.setEmail(obj.getEmail());
        dto.setAboutMe(obj.getAboutMe());
        dto.setGender(obj.getGender());
        dto.setBirthDate(obj.getBirthDate());
        if (obj.getBirthDate() != null) {
            dto.setAge(Math.toIntExact(ChronoUnit.YEARS.between(obj.getBirthDate(), LocalDate.now())));
        }


        return dto;
    }
}
