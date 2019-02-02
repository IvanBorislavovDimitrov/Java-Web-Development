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
import java.util.List;

@WebServlet("/tubes/all")
public class AllTubesServlet extends HttpServlet {

    @Inject
    private TubeService tubeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubeServiceDto> tubeServiceDtos = tubeService.getAll();
        req.setAttribute("tubes", tubeServiceDtos);
        req.getRequestDispatcher("/views/all-tubes.jsp").forward(req, resp);
    }
}
