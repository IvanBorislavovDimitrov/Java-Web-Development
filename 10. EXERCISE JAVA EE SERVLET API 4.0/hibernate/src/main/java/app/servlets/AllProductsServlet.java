package app.servlets;

import app.entities.Product;
import app.enums.Type;
import app.processors.FileContentProcessor;
import app.services.ProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/products/all")
public class AllProductsServlet extends HttpServlet {

    private final FileContentProcessor fileContentProcessor;
    private final ProductService productService;

    @Inject
    public AllProductsServlet(FileContentProcessor fileContentProcessor, ProductService productService) {
        this.fileContentProcessor = fileContentProcessor;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileContent = fileContentProcessor.readContent("templates/all-products.html");
        List<Product> products = productService.getAllProducts()
                .stream()
                .map(p -> new Product(p.getName(), p.getDescription(), Type.valueOf(p.getType())))
                .collect(Collectors.toList());

        fileContent = fileContent
                .replace("${products}", products.stream()
                        .map(p -> String.format("<li><a href=\"/products/details?name=%s\">%s</a></li>", p.getName(), p.getName()))
                        .collect(Collectors.joining("")));

        resp.getWriter().println(fileContent);
    }
}
