package org.alten.server.api.controller.standard;

import org.alten.business.http.request.CreateProductRequest;
import org.alten.business.http.request.UpdateProductRequest;
import org.alten.business.workflow.ProductWorkflow;
import org.alten.server.api.ProductApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductApi {
    private final ProductWorkflow productWorkflow;

    public ProductController(ProductWorkflow productWorkflow) {
        this.productWorkflow = productWorkflow;
    }

    @Override
    public ResponseEntity<?> getProduct(Long productId) {
        return ResponseEntity.ok(productWorkflow.getProduct(productId));
    }

    @Override
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productWorkflow.getProducts());
    }

    @Override
    public ResponseEntity<?> createProduct(CreateProductRequest createProductRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productWorkflow.createProduct(createProductRequest));
    }

    @Override
    public ResponseEntity<?> updateProduct(UpdateProductRequest updateProductRequest, Long productId) {
        return ResponseEntity.ok(productWorkflow.updateProduct(updateProductRequest, productId));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long productId) {
        return ResponseEntity.ok(productWorkflow.deleteProduct(productId));
    }
}
