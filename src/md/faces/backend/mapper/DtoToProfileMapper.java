package md.faces.backend.mapper;

import md.faces.backend.dto.ProfilegetDto;
import md.faces.backend.model.Profile;

public class DtoToProfileMapper implements Mapper<ProfilegetDto, Profile> {

    private static final DtoToProfileMapper INSTANCE = new DtoToProfileMapper();

    private DtoToProfileMapper() {}

    public static DtoToProfileMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Profile from(ProfilegetDto obj){
        return from(obj, new Profile());
    }


    @Override
    public Profile from(ProfilegetDto obj, Profile profile) {

        if (obj.getId() != null) {
            profile.setId(obj.getId());
        }
        if (obj.getFirstName() != null) {
            profile.setFirstName(obj.getFirstName());
        }
        if (obj.getLastName() != null) {
            profile.setLastName(obj.getLastName());
        }
        if (obj.getEmail() != null) {
            profile.setEmail(obj.getEmail());
        }
        if (obj.getGender() != null) {
            profile.setGender(obj.getGender());
        }
        if (obj.getBirthDate() != null) {
            profile.setBirthDate(obj.getBirthDate());
        }
        if (obj.getAboutMe() != null) {
            profile.setAboutMe(obj.getAboutMe());
        }

        return profile;
    }
}
