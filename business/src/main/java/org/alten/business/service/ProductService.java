package org.alten.business.service;

import org.alten.business.exception.ResourceNotCreatedException;
import org.alten.shared.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto) throws ResourceNotCreatedException;

    ProductDto updateProduct(ProductDto productDto, Long productId);

    void deleteProduct(Long productId);

    ProductDto getProduct(Long productId);

    List<ProductDto> getProducts();
}
