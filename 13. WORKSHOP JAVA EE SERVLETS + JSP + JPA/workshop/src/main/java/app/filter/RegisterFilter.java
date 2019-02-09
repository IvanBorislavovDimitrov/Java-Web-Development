package app.filter;

import app.model.UserRegisterBindingEntity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static app.constants.Constants.USER_REGISTER_MODEL;

@WebFilter("/register")
public class RegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");

        UserRegisterBindingEntity userRegisterBindingEntity =
                new UserRegisterBindingEntity(username, password, confirmPassword, email);

        request.setAttribute(USER_REGISTER_MODEL, userRegisterBindingEntity);

        chain.doFilter(request, response);
    }

}
