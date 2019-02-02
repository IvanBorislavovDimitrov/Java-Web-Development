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

@WebServlet("/products/details")
public class ProductServlet extends HttpServlet {

    private final ProductService productService;

    private final FileContentProcessor fileContentProcessor;

    @Inject
    public ProductServlet(ProductService productService, FileContentProcessor fileContentProcessor) {
        this.productService = productService;
        this.fileContentProcessor = fileContentProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("name");
        ProductDto product = productService.getProduct(productName);

        if (productName == null) {
            String fileContent = fileContentProcessor.readContent("templates/file-not-found.html");
            resp.setContentLength(fileContent.length());
            resp.setContentType("text/html");
            resp.getWriter().println(fileContent);
        } else {
            String fileContent = fileContentProcessor.readContent("templates/product.html");
            fileContent = fileContent.replace("${productName}", product.getName());
            fileContent = fileContent.replace("${description}", product.getDescription());
            fileContent = fileContent.replace("${type}", product.getType());

            resp.setContentLength(fileContent.length());
            resp.setContentType("text/html");
            resp.getWriter().println(fileContent);
        }

    }
}
