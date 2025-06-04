package md.faces.backend.mapper;

import jakarta.servlet.http.HttpServletRequest;
import md.faces.backend.dto.ProfileUpdateDto;
import md.faces.backend.model.Gender;
import md.faces.backend.utilits.StringUtilits;

import java.time.LocalDate;

import static md.faces.backend.utilits.StringUtilits.isBlank;

public class ReqToProfileUpdateDtoMapper implements Mapper<HttpServletRequest, ProfileUpdateDto> {

    private static final ReqToProfileUpdateDtoMapper INSTANCE = new ReqToProfileUpdateDtoMapper();

    private ReqToProfileUpdateDtoMapper() {
    }

    public static ReqToProfileUpdateDtoMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public ProfileUpdateDto mapFrom(HttpServletRequest obj) {
        return mapFrom(obj, new ProfileUpdateDto());
    }

    @Override
    public ProfileUpdateDto mapFrom(HttpServletRequest req, ProfileUpdateDto profileUpdateDto) {
        String id = req.getParameter("id");
        if (!isBlank(id)){
            profileUpdateDto.setId(Long.parseLong(id));
        }
        String email = req.getParameter("email");
        if (!isBlank(email)) {
            profileUpdateDto.setEmail(email);
        }
        profileUpdateDto.setFirstName(req.getParameter("firstName"));
        profileUpdateDto.setLastName(req.getParameter("lastName"));
        String birthDate = req.getParameter("birthDate");
        if (!isBlank(birthDate)) {
            profileUpdateDto.setBirthDate(LocalDate.parse(birthDate));
        }
        profileUpdateDto.setAboutMe(req.getParameter("aboutMe"));
        String gender = req.getParameter("gender");
        if (!isBlank(gender)) {
            profileUpdateDto.setGender(Gender.valueOf(gender));
        }
        return profileUpdateDto;
    }
}
