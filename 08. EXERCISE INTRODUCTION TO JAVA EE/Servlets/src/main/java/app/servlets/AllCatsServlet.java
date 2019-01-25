package app.servlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.entitities.Cat;
import app.processors.FileContentProcessor;

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {
	
	@Inject
	private FileContentProcessor fileContentProcessor;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Cat> cats = (List<Cat>) getServletContext().getAttribute("cats");
		if (cats == null || cats.size() == 0) {
			String fileContent = fileContentProcessor.readContent("templates/no-cats.html");
			setResponseProperties(resp, fileContent);
		} else {
			String catRoutes = getCatRoutes(cats);
			String fileContent = fileContentProcessor.readContent("templates/all-cats.html");
			fileContent = fileContent.replace("${cats}", catRoutes);
			setResponseProperties(resp, fileContent);
		}
	}
	
	private void setResponseProperties(HttpServletResponse resp, String fileContent) throws IOException {
		resp.setContentType("text/html");
		resp.setContentLength(fileContent.length());
		resp.getWriter().println(fileContent);
	}
	
	private String getCatRoutes(List<Cat> cats) {
		StringBuilder sb = new StringBuilder();
		cats.stream()
				.map(c -> String.format("<h3><a class=\"cool\" href=\"/cats/profile?catName=%s\">%s</a></h3>", c.getName(), c.getName()))
				.forEach(sb::append);
		
		return sb.toString();
	}
}
