package md.faces.backend.mapper;

import md.faces.backend.dto.ProfileGetDto;
import md.faces.backend.model.Profile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProfileToDtoMapper implements Mapper<Profile, ProfileGetDto>{

    private static final ProfileToDtoMapper INSTANCE = new ProfileToDtoMapper();

    private ProfileToDtoMapper() {}

    public static ProfileToDtoMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public ProfileGetDto mapFrom(Profile obj) {
        return mapFrom(obj, new ProfileGetDto());
    }

    @Override
    public ProfileGetDto mapFrom(Profile obj, ProfileGetDto dto) {
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
