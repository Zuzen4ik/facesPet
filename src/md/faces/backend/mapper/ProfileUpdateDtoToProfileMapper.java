package md.faces.backend.mapper;

import md.faces.backend.dto.ProfileUpdateDto;
import md.faces.backend.model.Profile;

public class ProfileUpdateDtoToProfileMapper implements Mapper<ProfileUpdateDto, Profile> {

    private static final ProfileUpdateDtoToProfileMapper INSTANCE = new ProfileUpdateDtoToProfileMapper();

    private ProfileUpdateDtoToProfileMapper() {
    }

    public static ProfileUpdateDtoToProfileMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Profile mapFrom(ProfileUpdateDto obj) {
        return mapFrom(obj, new Profile());
    }

    @Override
    public Profile mapFrom(ProfileUpdateDto obj, Profile profile) {
        profile.setId(obj.getId());
        if (obj.getFirstName() != null) {
            profile.setFirstName(obj.getFirstName());
        }
        if (obj.getLastName() != null) {
            profile.setLastName(obj.getLastName());
        }
        if (obj.getEmail() != null) {
            profile.setEmail(obj.getEmail());
        }
        if (obj.getAboutMe() != null) {
            profile.setAboutMe(obj.getAboutMe());
        }
        if (obj.getBirthDate() != null) {
            profile.setBirthDate(obj.getBirthDate());
        }
        if (obj.getGender() != null) {
            profile.setGender(obj.getGender());
        }
        return profile;
    }
}
