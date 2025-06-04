package md.faces.backend.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import md.faces.backend.dto.ProfileGetDto;
import md.faces.backend.dto.ProfileUpdateDto;
import md.faces.backend.mapper.ReqToProfileUpdateDtoMapper;
import md.faces.backend.service.ProfileService;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    private final  ProfileService profService = ProfileService.getInstance();
    private final ReqToProfileUpdateDtoMapper reqToProfileUpdateDtoMapper = ReqToProfileUpdateDtoMapper.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String url = "/notFound";

        if (id != null) {
            Optional<ProfileGetDto> optional = profService.findById(Long.parseLong(id));
            if (optional.isPresent()) {
                req.setAttribute("profile", optional.get());
                url = "WEB-INF/jsp/profile.jsp";
            }
        } else {
            req.setAttribute("profiles", profService.findAll());
            url = "WEB-INF/jsp/profiles.jsp";
        }
        req.getRequestDispatcher(url).forward(req, resp);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProfileUpdateDto profile = reqToProfileUpdateDtoMapper.mapFrom(req);
        profService.update(profile);
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sId = req.getParameter("id");
        if (!sId.isBlank()) {
            profService.delete(Long.parseLong(sId));
        }
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        resp.sendRedirect("/registration");
    }
}
