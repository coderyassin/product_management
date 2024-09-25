package org.alten.business.workflow.impl.standard;

import org.alten.business.exception.ResourceNotFoundException;
import org.alten.business.http.request.CreateProductRequest;
import org.alten.business.http.request.UpdateProductRequest;
import org.alten.business.http.response.CreateProductResponse;
import org.alten.business.http.response.DeleteProductResponse;
import org.alten.business.http.response.UpdateProductResponse;
import org.alten.business.mapper.ProductMapper;
import org.alten.business.service.ProductService;
import org.alten.business.workflow.ProductWorkflow;
import org.alten.core.entity.Product;
import org.alten.core.repository.ProductRepository;
import org.alten.shared.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductWorkflowImpl implements ProductWorkflow {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductWorkflowImpl(ProductService productService,
                               ProductRepository productRepository,
                               ProductMapper productMapper) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        ProductDto productDto = productService.createProduct(productMapper.toDto(createProductRequest));
        return productMapper.toCreateProductResponse(productDto);
    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " does not exist"));

        ProductDto productDto = buildProductDto(updateProductRequest, product);

        return productMapper.toUpdateProductResponse(productService.updateProduct(productDto, productId));
    }

    @Override
    public DeleteProductResponse deleteProduct(Long productId) {
        ProductDto productDto = productService.getProduct(productId);
        productService.deleteProduct(productId);
        return productMapper.toDeleteProductResponse(productDto);
    }

    @Override
    public ProductDto getProduct(Long productId) {
        return productService.getProduct(productId);
    }

    @Override
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    private ProductDto buildProductDto(UpdateProductRequest updateProductRequest, Product product) {
        return ProductDto.builder()
                .code(Objects.nonNull(updateProductRequest.getCode()) ? updateProductRequest.getCode() : product.getCode())
                .name(Objects.nonNull(updateProductRequest.getName()) ? updateProductRequest.getName() : product.getName())
                .description(Objects.nonNull(updateProductRequest.getDescription()) ? updateProductRequest.getDescription() : product.getDescription())
                .image(Objects.nonNull(updateProductRequest.getImage()) ? updateProductRequest.getImage() : product.getImage())
                .category(Objects.nonNull(updateProductRequest.getCategory()) ? updateProductRequest.getCategory() : product.getCategory())
                .price(Objects.nonNull(updateProductRequest.getPrice()) ? updateProductRequest.getPrice() : product.getPrice())
                .quantity(Objects.nonNull(updateProductRequest.getQuantity()) ? updateProductRequest.getQuantity() : product.getQuantity())
                .internalReference(Objects.nonNull(updateProductRequest.getInternalReference()) ? updateProductRequest.getInternalReference() : product.getInternalReference())
                .shellId(Objects.nonNull(updateProductRequest.getShellId()) ? updateProductRequest.getShellId() : product.getShellId())
                .inventoryStatus(Objects.nonNull(updateProductRequest.getInventoryStatus()) ? updateProductRequest.getInventoryStatus() : product.getInventoryStatus())
                .rating(Objects.nonNull(updateProductRequest.getRating()) ? updateProductRequest.getRating() : product.getRating())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
