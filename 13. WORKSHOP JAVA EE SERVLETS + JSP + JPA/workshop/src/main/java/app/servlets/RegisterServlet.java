package app.servlets;

import app.model.UserRegisterBindingEntity;
import app.services.api.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constants.Constants.USER_REGISTER_MODEL;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService;

    @Inject
    public RegisterServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegisterBindingEntity userRegisterBindingEntity = (UserRegisterBindingEntity) req.getAttribute(USER_REGISTER_MODEL);

        try {
            userService.persist(userRegisterBindingEntity);
        } catch (IllegalArgumentException e) {
            resp.sendRedirect("/register");
            return;
        }

        resp.sendRedirect("/home");
    }
}
