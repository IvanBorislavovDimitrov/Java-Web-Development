package app.servlets;

import app.mappers.Mapper;
import app.model.views.TubeViewModel;
import app.services.api.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static app.constants.Constants.LOGGED_USER;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private final TubeService tubeService;
    private final Mapper mapper;

    @Inject
    public HomeServlet(TubeService tubeService, Mapper mapper) {
        this.tubeService = tubeService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(LOGGED_USER) == null) {
            resp.sendRedirect("/");
            return;
        }
        List<TubeViewModel> tubeViewModels = getTubeViewModels();
        req.setAttribute("tubes", tubeViewModels);

        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }

    private List<TubeViewModel> getTubeViewModels() {
        return tubeService.getAllApprovedTubes().stream()
                .map(tubeBindingEntity -> mapper.map(tubeBindingEntity, TubeViewModel.class))
                .collect(Collectors.toList());
    }
}
