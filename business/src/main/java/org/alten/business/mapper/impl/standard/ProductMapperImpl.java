package org.alten.business.mapper.impl.standard;

import org.alten.business.http.request.CreateProductRequest;
import org.alten.business.http.request.UpdateProductRequest;
import org.alten.business.http.response.CreateProductResponse;
import org.alten.business.http.response.DeleteProductResponse;
import org.alten.business.http.response.UpdateProductResponse;
import org.alten.business.mapper.ProductMapper;
import org.alten.core.entity.Product;
import org.alten.shared.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductMapperImpl implements ProductMapper {
    private final ModelMapper mapper;

    public ProductMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        return mapper.map(productDto, Product.class);
    }

    @Override
    public ProductDto toDto(Product product) {
        return Objects.nonNull(product) ? mapper.map(product, ProductDto.class) : null;
    }

    @Override
    public ProductDto toDto(CreateProductRequest createProductRequest) {
        return Objects.nonNull(createProductRequest) ? mapper.map(createProductRequest, ProductDto.class) : null;
    }

    @Override
    public ProductDto toDto(UpdateProductRequest updateProductRequest) {
        return Objects.nonNull(updateProductRequest) ? mapper.map(updateProductRequest, ProductDto.class) : null;
    }

    @Override
    public CreateProductResponse toCreateProductResponse(ProductDto productDto) {
        return Objects.nonNull(productDto) ? mapper.map(productDto, CreateProductResponse.class) : null;
    }

    @Override
    public UpdateProductResponse toUpdateProductResponse(ProductDto productDto) {
        return Objects.nonNull(productDto) ? mapper.map(productDto, UpdateProductResponse.class) : null;
    }

    @Override
    public DeleteProductResponse toDeleteProductResponse(ProductDto productDto) {
        return Objects.nonNull(productDto) ? mapper.map(productDto, DeleteProductResponse.class) : null;
    }

}
