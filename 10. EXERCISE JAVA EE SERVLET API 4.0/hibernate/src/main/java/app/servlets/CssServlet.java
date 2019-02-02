package app.servlets;

import app.processors.FileContentProcessor;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/style.css")
public class CssServlet extends HttpServlet {

    private final FileContentProcessor fileContentProcessor;

    @Inject
    public CssServlet(FileContentProcessor fileContentProcessor) {
        this.fileContentProcessor = fileContentProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileContent = fileContentProcessor.readContent("templates/style.css");
        resp.getWriter().println(fileContent);
    }
}
