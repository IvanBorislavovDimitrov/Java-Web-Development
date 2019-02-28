package app.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/home", "/schedules/create", "/schedules/print",
        "/schedules/details", "/jsf/create-schedule.xhtml", "/jsf/details-schedule.xhtml", "/jsf/print-schedule.xhtml"})
public class CheckForLoggedUserFilter extends RedirectIfNotLoggedFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        redirectIfNotLogged(request, response, chain);
    }
}
