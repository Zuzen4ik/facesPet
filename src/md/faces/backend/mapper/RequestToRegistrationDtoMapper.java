package md.faces.backend.mapper;

import com.sun.net.httpserver.Request;
import jakarta.servlet.http.HttpServletRequest;
import md.faces.backend.dto.RegistrationDto;

public class RequestToRegistrationDtoMapper implements Mapper<HttpServletRequest, RegistrationDto> {

    private static final RequestToRegistrationDtoMapper INSTANCE = new RequestToRegistrationDtoMapper();

    private RequestToRegistrationDtoMapper() {
    }

    public static RequestToRegistrationDtoMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public RegistrationDto mapFrom(HttpServletRequest req) {
        return mapFrom(req, new RegistrationDto());
    }

    @Override
    public RegistrationDto mapFrom(HttpServletRequest req, RegistrationDto dto) {
        dto.setEmail(req.getParameter("email"));
        dto.setPassword(req.getParameter("password"));
        return dto;
    }
}
