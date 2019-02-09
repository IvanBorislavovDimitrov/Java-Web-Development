package app.servlets;

import app.enums.UserRole;
import app.model.views.TubeViewModel;
import app.model.views.UserSessionEntity;
import app.services.api.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static app.constants.Constants.LOGGED_USER;

@WebServlet("/admin/tube/pending")
public class PendingTubesService extends HttpServlet {

    private final TubeService tubeService;

    @Inject
    public PendingTubesService(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSessionEntity userSessionEntity = (UserSessionEntity) req.getSession().getAttribute(LOGGED_USER);
        if (userSessionEntity == null || !userSessionEntity.getUserRole().equals(UserRole.ADMIN)) {
            resp.sendRedirect("/");
            return;
        }
        List<TubeViewModel> pendingTubes = tubeService.getAllPendingTubes();
        req.setAttribute("pendings", pendingTubes);

        req.getRequestDispatcher("/jsp/pendings.jsp").forward(req, resp);
    }
}
