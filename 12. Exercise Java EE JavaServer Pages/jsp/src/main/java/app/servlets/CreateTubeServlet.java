package app.servlets;

import app.dtos.TubeServiceDto;
import app.services.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class CreateTubeServlet extends HttpServlet {

    @Inject
    private TubeService tubeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String youtubeLink = req.getParameter("youtubeLink");
        String uploader = req.getParameter("uploader");

        TubeServiceDto tubeServiceDto = new TubeServiceDto(title, description, youtubeLink, uploader);
        tubeService.save(tubeServiceDto);
        resp.sendRedirect("/");
    }
}
