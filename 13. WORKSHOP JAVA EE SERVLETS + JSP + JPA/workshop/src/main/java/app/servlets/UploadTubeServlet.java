package app.servlets;

import app.model.TubeBindingEntity;
import app.model.views.UserSessionEntity;
import app.services.api.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constants.Constants.*;


@WebServlet("/tube/upload")
public class UploadTubeServlet extends HttpServlet {

    private final TubeService tubeService;

    @Inject
    public UploadTubeServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(LOGGED_USER) == null) {
            resp.sendRedirect("/");
            return;
        }
        req.getRequestDispatcher("/jsp/upload-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(LOGGED_USER) == null) {
            resp.sendRedirect("/");
            return;
        }
        TubeBindingEntity tubeBindingModel = (TubeBindingEntity) req.getAttribute(UPLOAD_TUBE);
        UserSessionEntity userSessionEntity = (UserSessionEntity) req.getSession().getAttribute(LOGGED_USER);
        tubeBindingModel.setUploader(userSessionEntity.getUsername());

        tubeService.persist(tubeBindingModel);

        resp.sendRedirect("/home");
    }

}
