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

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {

	@Inject
	private FileContentProcessor fileContentProcessor;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileContent = fileContentProcessor.readContent("templates/create-a-cat.html");
		resp.setContentType("text/html");
		resp.setContentLength(fileContent.length());
		resp.getWriter().println(fileContent);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Cat> cats = (List<Cat>) getServletContext().getAttribute("cats");
		if (cats == null) {
			cats = new ArrayList<>();
			getServletContext().setAttribute("cats", cats);
			return;
		}
		String name = req.getParameter("name");
		String breed = req.getParameter("breed");
		String color = req.getParameter("color");
		Integer numberOfLegs = null;
		try {
			numberOfLegs = Integer.parseInt(req.getParameter("numberOfLegs"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		Cat cat = new Cat.Builder().name(name).breed(breed).color(color).numberOfLegs(numberOfLegs).build();

		cats.add(cat);
		resp.sendRedirect("/cats/profile?catName=" + name);
	}
}
