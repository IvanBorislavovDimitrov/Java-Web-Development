package app.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.entitities.Cat;
import app.processors.FileContentProcessor;

// This homework has been written in the Eclipse IDE, for the best compatibility use it.

@WebServlet("/")
public class HomeServlet extends HttpServlet {

	@Inject
	private FileContentProcessor fileContentProcessor;
	
	private List<Cat> cats = new ArrayList<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileContent = fileContentProcessor.readContent("templates/index.html");
		
		getServletContext().setAttribute("cats", cats);
		resp.setContentType("text/html");
		resp.setContentLength(fileContent.length());
		resp.getWriter().println(fileContent);
	}
}
