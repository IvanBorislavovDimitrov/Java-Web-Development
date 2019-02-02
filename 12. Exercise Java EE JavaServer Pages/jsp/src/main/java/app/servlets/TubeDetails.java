package app.servlets;

import app.dtos.TubeServiceDto;
import app.repositories.TubeRepository;
import app.services.TubeService;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/details")
public class TubeDetails extends HttpServlet {

    @Inject
    private TubeService tubeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tubeName = req.getParameter("tubeName");
        try {
            TubeServiceDto tubeServiceDto = tubeService.getObj(tubeName);
            req.setAttribute("tube", tubeServiceDto);
            req.getRequestDispatcher("/views/tube-details.jsp").forward(req, resp);
        } catch (NoResultException e) {
            req.getRequestDispatcher("/views/not-found.jsp").forward(req, resp);
        }

    }
}
