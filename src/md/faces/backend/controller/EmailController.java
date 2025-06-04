package md.faces.backend.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import md.faces.backend.dto.ProfileGetDto;
import md.faces.backend.dto.ProfileUpdateDto;
import md.faces.backend.mapper.Mapper;
import md.faces.backend.mapper.ReqToProfileUpdateDtoMapper;
import md.faces.backend.model.exceptions.DuplicateEmailException;
import md.faces.backend.service.ProfileService;

import java.io.IOException;
import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet("/email")
public class EmailController extends HttpServlet {


    private final ProfileService profService = ProfileService.getInstance();
    private final ReqToProfileUpdateDtoMapper reqToProfileUpdateDtoMapper = ReqToProfileUpdateDtoMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String url = null;

        if (id != null) {
            Optional<ProfileGetDto> optional = profService.findById(Long.parseLong(id));
            if (optional.isPresent()) {
                req.setAttribute("profile", optional.get());
                url = "WEB-INF/jsp/email.jsp";
            }
        }
        if (url == null) {
            resp.sendError(SC_NOT_FOUND);
        }
        else {
            req.getRequestDispatcher(url).forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String email = req.getParameter("email");
        String repeatEmail = req.getParameter("repeatEmail");
        String id = req.getParameter("id");

        if (email == null || !email.equals(repeatEmail)) {
            req.setAttribute("errorMessage", "Emails do not match.");
            profService.findById(Long.parseLong(id)).ifPresent(profile -> req.setAttribute("profile", profile));
            req.getRequestDispatcher("WEB-INF/jsp/email.jsp").forward(req, resp);
            return;
        } else {
            ProfileUpdateDto profile = reqToProfileUpdateDtoMapper.mapFrom(req);
            try {
                profService.update(profile);
                resp.sendRedirect(String.format("/profile?id=%s", profile.getId()));
            } catch (DuplicateEmailException e) {
                resp.sendError(SC_BAD_REQUEST);
            }
        }
    }
}
