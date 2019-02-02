package app.services;

import app.dtos.ProductDto;
import app.entities.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProduct(String name);

    void save(ProductDto product);
}
