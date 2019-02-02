package app.servlets;

import app.processors.FileContentProcessor;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/styles/*")
public class CssServlet extends HttpServlet {

    @Inject
    private FileContentProcessor fileContentProcessor;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resource = req.getRequestURL().toString().substring(req.getRequestURL().toString().lastIndexOf("/") + 1);
        resp.getWriter().println(fileContentProcessor.readFile("styles/" + resource));
    }
}
