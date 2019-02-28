package app.filter;

import app.constants.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/jsf/index.xhtml", "/login", "/register", "/jsf/login.xhtml", "/jsf/register.xhtml"})
public class CheckForLoggedUser implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        if (httpSession.getAttribute(Constants.LOGGED_USER) != null) {
            ((HttpServletResponse) response).sendRedirect("/home");
            return;
        }

        chain.doFilter(request, response);
    }
}
