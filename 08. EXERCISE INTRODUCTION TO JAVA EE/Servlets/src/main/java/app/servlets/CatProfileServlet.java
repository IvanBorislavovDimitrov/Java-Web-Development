package app.servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.entitities.Cat;
import app.processors.FileContentProcessor;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {
	
	@Inject
	private FileContentProcessor fileContentProcessor;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Cat> cats = (List<Cat>) getServletContext().getAttribute("cats");
		String catName = req.getParameter("catName");
		Cat cat = getCatWithName(catName, cats);
		if (cat == null) {
			String fileContent = fileContentProcessor.readContent("templates/cats-not-found.html");
			fileContent = fileContent.replace("${name}", catName == null ? "" : catName);
			resp.setContentLength(fileContent.length());
			resp.setContentType("text/html");
			resp.getWriter().println(fileContent);
		} else {
			String fileContent = fileContentProcessor.readContent("templates/cats-profile.html");
			fileContent = fileContent.replace("${name}", catName);
			fileContent = fileContent.replace("${breed}", cat.getBreed());
			fileContent = fileContent.replace("${color}", cat.getColor());
			fileContent = fileContent.replace("${numberOfLegs}", String.valueOf(cat.getNumberOfLegs()));
			resp.setContentLength(fileContent.length());
			resp.setContentType("text/html");
			resp.getWriter().println(fileContent);
		}
		
	}
	
	private Cat getCatWithName(String catName, List<Cat> cats) {
		if (cats == null) {
			return null;
		}
		return cats.stream()
				.filter(c -> c.getName().equals(catName))
				.findAny()
				.orElse(null);
	}

}
