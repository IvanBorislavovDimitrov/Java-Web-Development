package app.filters;

import app.constatns.Constants;
import app.enums.Role;

import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/packages/create")
public class PackageCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        if (httpSession == null) {
            ((HttpServletResponse) response).sendRedirect("/");
            return;
        }

        String userRole = (String) httpSession.getAttribute(Constants.USER_ROLE);
        if (userRole == null || !Role.ADMIN.equals(Role.valueOf(userRole))) {
            ((HttpServletResponse) response).sendRedirect("/");
            return;
        }

        chain.doFilter(request, response);
    }
}
