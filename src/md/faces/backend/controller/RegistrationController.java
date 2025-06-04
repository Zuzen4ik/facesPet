package md.faces.backend.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import md.faces.backend.dto.ProfileGetDto;
import md.faces.backend.dto.RegistrationDto;
import md.faces.backend.mapper.RequestToRegistrationDtoMapper;
import md.faces.backend.model.Gender;
import md.faces.backend.model.Profile;
import md.faces.backend.service.ProfileService;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private final ProfileService service = ProfileService.getInstance();

    private final RequestToRegistrationDtoMapper requestToRegistrationDtoMapper = RequestToRegistrationDtoMapper.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        if (password == null || !password.equals(repeatPassword)) {
            req.setAttribute("errorMessage", "Passwords do not match.");
            req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
            return;
        } else {
            RegistrationDto dto = requestToRegistrationDtoMapper.mapFrom(req);
            Long id = service.save(dto);
            resp.sendRedirect(String.format("/profile?id=%s", id));
        }
    }
}
