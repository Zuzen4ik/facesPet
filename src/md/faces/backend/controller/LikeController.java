package md.faces.backend.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import md.faces.backend.service.LikeService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpClient;

@WebServlet("/like")
public class LikeController extends HttpServlet {

    private final LikeService likeService = LikeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String ans = "15";
        if (id != null) {
            long l = Long.parseLong(id);
            long likesById = likeService.getLikesById(l);
            ans = likesById + "";
        }


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.write(ans);
        }

    }
}
