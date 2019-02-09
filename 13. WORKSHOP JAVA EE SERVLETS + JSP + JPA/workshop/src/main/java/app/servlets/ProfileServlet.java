package app.servlets;

import app.model.TubeBindingEntity;
import app.model.UserRegisterBindingEntity;
import app.model.views.UserSessionEntity;
import app.model.views.UserViewModel;
import app.services.api.TubeService;
import app.services.api.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static app.constants.Constants.LOGGED_USER;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private final TubeService tubeService;
    private final UserService userService;

    @Inject
    public ProfileServlet(TubeService tubeService, UserService userService) {
        this.tubeService = tubeService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(LOGGED_USER) == null) {
            resp.sendRedirect("/");
            return;
        }
        UserSessionEntity userSessionEntity = (UserSessionEntity) req.getSession().getAttribute(LOGGED_USER);
        List<TubeBindingEntity> tubes = tubeService.getAllByUsername(userSessionEntity.getUsername());

        req.setAttribute("user", getUserViewModel(userSessionEntity.getUsername()));
        req.setAttribute("tubes", tubes);

        req.getRequestDispatcher("/jsp/user-profile.jsp").forward(req, resp);
    }

    private UserViewModel getUserViewModel(String username) {
        UserRegisterBindingEntity userRegisterBindingEntity = userService.getObj(username);

        return new UserViewModel(userRegisterBindingEntity.getUsername(),
                userRegisterBindingEntity.getEmail());
    }
}
