package app.servlets;

import app.dtos.ProductDto;
import app.processors.FileContentProcessor;
import app.services.ProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {

    private final FileContentProcessor fileContentProcessor;
    private final ProductService productService;

    @Inject
    public ProductCreateServlet(FileContentProcessor fileContentProcessor, ProductService productService) {
        this.fileContentProcessor = fileContentProcessor;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileContent = fileContentProcessor.readContent("templates/create-a-product.html");
        resp.getWriter().println(fileContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String type = req.getParameter("type");

        try {
            ProductDto product = new ProductDto(name, description, type.toUpperCase());
            productService.save(product);
        } catch (IllegalArgumentException e) {
            resp.getWriter().write(e.getMessage());
            resp.sendRedirect("/products/create");
            return;
        }
        resp.sendRedirect("/");
    }
}
