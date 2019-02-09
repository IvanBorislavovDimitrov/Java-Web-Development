package app.servlets;

import app.enums.TubeStatus;
import app.services.api.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/status")
public class ApproveDeclineTubeServlet extends HttpServlet {

    private static final String TRUE = "true";

    private static final String FALSE = "false";

    private final TubeService tubeService;

    @Inject
    public ApproveDeclineTubeServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("tubeUuid");
        String approveTube = req.getParameter("approve");
        if (approveTube == null) {
            resp.sendRedirect("/");
            return;
        }

        if (approveTube.equals(TRUE)) {
            tubeService.setTubeStatus(uuid, TubeStatus.APPROVED);
        } else if (approveTube.equals(FALSE)) {
            tubeService.setTubeStatus(uuid, TubeStatus.DECLINED);
        }

        resp.sendRedirect("/home");
    }
}
