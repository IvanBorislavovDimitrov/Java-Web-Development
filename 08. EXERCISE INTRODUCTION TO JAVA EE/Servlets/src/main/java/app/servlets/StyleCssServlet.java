package app.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.processors.FileContentProcessor;

@WebServlet("/style.css")
public class StyleCssServlet extends HttpServlet {
	
	@Inject
	private FileContentProcessor fileContentProcessor;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileContent = fileContentProcessor.readContent("templates/style.css");
		
		resp.setContentType("text/css");
		resp.setContentLength(fileContent.length());
		resp.getWriter().println(fileContent);
	}

}
