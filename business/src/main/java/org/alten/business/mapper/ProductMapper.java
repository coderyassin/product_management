package org.alten.business.mapper;

import org.alten.business.http.request.CreateProductRequest;
import org.alten.business.http.request.UpdateProductRequest;
import org.alten.business.http.response.CreateProductResponse;
import org.alten.business.http.response.DeleteProductResponse;
import org.alten.business.http.response.UpdateProductResponse;
import org.alten.core.entity.Product;
import org.alten.shared.dto.ProductDto;

public interface ProductMapper {
    Product toEntity(ProductDto productDto);
    ProductDto toDto(Product product);
    ProductDto toDto(CreateProductRequest createProductRequest);
    ProductDto toDto(UpdateProductRequest updateProductRequest);
    CreateProductResponse toCreateProductResponse(ProductDto productDto);
    UpdateProductResponse toUpdateProductResponse(ProductDto productDto);
    DeleteProductResponse toDeleteProductResponse(ProductDto productDto);
}
