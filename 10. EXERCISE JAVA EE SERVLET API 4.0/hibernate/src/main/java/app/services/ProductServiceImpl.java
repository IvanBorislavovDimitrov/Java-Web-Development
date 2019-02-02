package app.services;

import app.dtos.ProductDto;
import app.entities.Product;
import app.enums.Type;
import app.repositories.ProductRepository;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.getAll()
                .stream()
                .map(p -> new ProductDto(p.getName(), p.getDescription(), p.getType().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProduct(String name) {
        Product product = productRepository.getObj(name);
        return new ProductDto(product.getName(), product.getDescription(), product.getType().toString());
    }

    @Override
    public void save(ProductDto productDto) {
        if (!Arrays.stream(Type.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(productDto.getType())) {
            throw new IllegalArgumentException("Invalid type chosen!");
        }
        Product product = new Product(productDto.getName(), productDto.getDescription(),
                Type.valueOf(productDto.getType()));
        productRepository.save(product);
    }


}
