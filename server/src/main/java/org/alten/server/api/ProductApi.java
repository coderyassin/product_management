package org.alten.server.api;

import org.alten.business.http.request.CreateProductRequest;
import org.alten.business.http.request.UpdateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.alten.server.util.ApiPaths.Product.*;

@RequestMapping(PRODUCTS)
public interface ProductApi {
    @GetMapping(PRODUCT_ID)
    ResponseEntity<?> getProduct(@PathVariable Long productId);

    @GetMapping
    ResponseEntity<?> getProducts();

    @PostMapping
    ResponseEntity<?> createProduct(@RequestBody CreateProductRequest createProductRequest);

    @PatchMapping(PRODUCT_ID)
    ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest updateProductRequest, @PathVariable Long productId);

    @DeleteMapping(PRODUCT_ID)
    ResponseEntity<?> deleteProduct(@PathVariable Long productId);
}
