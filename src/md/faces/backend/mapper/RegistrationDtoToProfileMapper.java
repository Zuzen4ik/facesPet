package md.faces.backend.mapper;

import md.faces.backend.dto.RegistrationDto;
import md.faces.backend.model.Profile;

public class RegistrationDtoToProfileMapper implements Mapper<RegistrationDto, Profile> {

    private static final RegistrationDtoToProfileMapper INSTANCE = new RegistrationDtoToProfileMapper();

    private RegistrationDtoToProfileMapper() {
    }

    public static RegistrationDtoToProfileMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Profile mapFrom(RegistrationDto dto) {
        return mapFrom(dto, new Profile());
    }

    @Override
    public Profile mapFrom(RegistrationDto dto, Profile profile) {
        profile.setEmail(dto.getEmail());
        profile.setPassword(dto.getPassword());
        return profile;
    }
}
