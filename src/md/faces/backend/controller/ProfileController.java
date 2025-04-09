package md.faces.backend.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import md.faces.backend.model.Gender;
import md.faces.backend.model.Profile;
import md.faces.backend.service.ProfileService;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    private final  ProfileService profService = ProfileService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String url = "/notFound";

        if (id != null) {
            Optional<Profile> optional = profService.findById(Long.parseLong(id));
            if (optional.isPresent()) {
                req.setAttribute("profile", optional.get());
                url = "WEB-INF/jsp/profile.jsp";
            }
        }
        req.getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Profile profile = getProfile(req);
        Long id = profService.save(profile).getId();
        resp.sendRedirect(String.format("/profile?id=%s", id));
    }



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Profile profile = getProfile(req);
        profService.update(profile);
        resp.sendRedirect(String.format("/profile?id=%s", profile.getId()));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sId = req.getParameter("id");
        if (!sId.isBlank()){
            profService.delete(Long.parseLong(sId));
        }
        resp.sendRedirect("/registration");
    }

    private Profile getProfile(HttpServletRequest req) {
        String sId = req.getParameter("id");

        Profile profile = new Profile();

        if (!sId.isBlank()){
            profile.setId(Long.parseLong(sId));
        }
        profile.setEmail(req.getParameter("email"));
        profile.setFirstName(req.getParameter("firstName"));
        profile.setLastName(req.getParameter("lastName"));
        profile.setAboutMe(req.getParameter("aboutMe"));
        profile.setGender(Gender.valueOf(req.getParameter("gender")));
        return profile;
    }
}
