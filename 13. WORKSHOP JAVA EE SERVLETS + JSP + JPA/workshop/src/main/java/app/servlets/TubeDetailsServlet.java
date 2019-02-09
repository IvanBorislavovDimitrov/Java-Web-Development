package app.servlets;

import app.entities.Tube;
import app.mappers.Mapper;
import app.model.TubeBindingEntity;
import app.model.views.TubeViewModel;
import app.services.api.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constants.Constants.LOGGED_USER;

@WebServlet("/tube/details/*")
public class TubeDetailsServlet extends HttpServlet {

    private final TubeService tubeService;
    private final Mapper mapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService, Mapper mapper) {
        this.tubeService = tubeService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tubeUuid = getTubeId(req);
        TubeViewModel tubeViewModel = mapper.map(tubeService.getObj(tubeUuid), TubeViewModel.class);

        if (req.getSession().getAttribute(LOGGED_USER) == null) {
            resp.sendRedirect("/");
            return;
        }

        tubeService.addView(tubeUuid);
        req.setAttribute("tube", tubeViewModel);
        req.getRequestDispatcher("/jsp/tube-details.jsp").forward(req, resp);
    }

    private String getTubeId(HttpServletRequest req) {

        return req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
    }

}
