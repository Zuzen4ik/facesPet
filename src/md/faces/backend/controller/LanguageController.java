package md.faces.backend.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/lang")
public class LanguageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");

        Cookie cookie = new Cookie("lang", "eng");

        if ("de".equals(lang)) {
            cookie.setValue("de");
        }
        resp.addCookie(cookie);

        //redirect to the same page
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }
}
