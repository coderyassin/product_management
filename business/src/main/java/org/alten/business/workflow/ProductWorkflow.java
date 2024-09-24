package org.alten.business.workflow;

import org.alten.business.http.request.CreateProductRequest;
import org.alten.business.http.request.UpdateProductRequest;
import org.alten.business.http.response.CreateProductResponse;
import org.alten.business.http.response.DeleteProductResponse;
import org.alten.business.http.response.UpdateProductResponse;
import org.alten.shared.dto.ProductDto;

import java.util.List;

public interface ProductWorkflow {
    CreateProductResponse createProduct(CreateProductRequest createProductRequest);

    UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest, Long productId);

    DeleteProductResponse deleteProduct(Long productId);

    ProductDto getProduct(Long productId);

    List<ProductDto> getProducts();
}
