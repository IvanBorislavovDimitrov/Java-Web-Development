package app.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/home", "/jobs/add"})
public class CheckForLoggedUserFilter extends RedirectIfNotLoggedFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        redirectIfNotLogged(request, response, chain);
    }
}
