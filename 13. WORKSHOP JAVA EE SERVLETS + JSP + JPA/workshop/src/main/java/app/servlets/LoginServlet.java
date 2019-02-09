package app.servlets;

import app.enums.UserRole;
import app.model.UserLoginBindingEntity;
import app.model.views.UserSessionEntity;
import app.services.api.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constants.Constants.LOGGED_USER;
import static app.constants.Constants.USER_LOGIN_MODEL;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService;

    @Inject
    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(LOGGED_USER) != null) {
            resp.sendRedirect("/home");
            return;
        }

        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginBindingEntity userLoginBindingEntity = (UserLoginBindingEntity) req.getAttribute(USER_LOGIN_MODEL);

        if (!userService.doesUserExist(userLoginBindingEntity.getUsername(), userLoginBindingEntity.getPassword())) {
            resp.sendRedirect("/login");
            return;
        }

        UserRole userRole = userService.getUserRole(userLoginBindingEntity.getUsername());
        UserSessionEntity userSessionEntity = new UserSessionEntity(userLoginBindingEntity.getUsername(), userRole);

        req.getSession().setAttribute(LOGGED_USER, userSessionEntity);

        resp.sendRedirect("/home");
    }
}
