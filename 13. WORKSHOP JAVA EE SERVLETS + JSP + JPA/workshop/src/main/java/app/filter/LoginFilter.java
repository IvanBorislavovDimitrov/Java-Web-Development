package app.filter;

import app.model.UserLoginBindingEntity;
import app.util.PasswordEncoder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static app.constants.Constants.USER_LOGIN_MODEL;

@WebFilter("/login")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String encodedPassword = PasswordEncoder.encode(password);

        UserLoginBindingEntity userLoginBindingEntity = new UserLoginBindingEntity(username, encodedPassword);

        request.setAttribute(USER_LOGIN_MODEL, userLoginBindingEntity);

        chain.doFilter(request, response);
    }
}
