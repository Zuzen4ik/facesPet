package md.faces.backend.mapper;

import jakarta.servlet.http.HttpServletRequest;
import md.faces.backend.dto.ProfilegetDto;
import md.faces.backend.model.Gender;

import java.time.LocalDate;

public class ServletRequestToProfileDtoMapper implements Mapper<HttpServletRequest, ProfilegetDto> {

    private static final ServletRequestToProfileDtoMapper INSTANCE = new ServletRequestToProfileDtoMapper();

    private ServletRequestToProfileDtoMapper() {}

    public static ServletRequestToProfileDtoMapper getInstance() {
        return INSTANCE;
    }





    @Override
    public ProfilegetDto from(HttpServletRequest req) {
        return from(req, new ProfilegetDto() );
    }

    @Override
    public ProfilegetDto from(HttpServletRequest req, ProfilegetDto dto) {
        String str = req.getParameter("id");
        if (str != null && !str.isBlank()) {
            dto.setId(Long.parseLong(str));
        }
        dto.setFirstName(req.getParameter("firstName"));
        dto.setLastName(req.getParameter("lastName"));
        dto.setEmail(req.getParameter("email"));
        dto.setGender(Gender.valueOf(req.getParameter("gender")));
        dto.setAboutMe(req.getParameter("aboutMe"));
        str = req.getParameter("birthDate");
        if (str != null && !str.isBlank()) {
            dto.setBirthDate(LocalDate.parse(str));
        }

        return dto;
    }
}
