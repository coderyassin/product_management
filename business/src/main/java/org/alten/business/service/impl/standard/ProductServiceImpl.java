package org.alten.business.service.impl.standard;

import lombok.extern.slf4j.Slf4j;
import org.alten.business.exception.ResourceNotCreatedException;
import org.alten.business.exception.ResourceNotDeletedException;
import org.alten.business.exception.ResourceNotFoundException;
import org.alten.business.exception.ResourceNotUpdatedException;
import org.alten.business.mapper.ProductMapper;
import org.alten.business.service.ProductService;
import org.alten.core.entity.Product;
import org.alten.core.repository.ProductRepository;
import org.alten.shared.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) throws ResourceNotCreatedException {
        try {
            Product product = productMapper.toEntity(productDto);
            return productMapper.toDto(productRepository.save(product));
        } catch (Exception e) {
            throw new ResourceNotCreatedException("An error occurred while creating the product " + productDto.toString(), e);
        }
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {
        try {
            Product productToBeSaved = productMapper.toEntity(productDto);
            productToBeSaved.setId(productId);
            return productMapper.toDto(productRepository.save(productToBeSaved));
        } catch (Exception e) {
            throw new ResourceNotUpdatedException("An error occurred while updating the product "
                    + productDto + " with id " + productId, e);
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw new ResourceNotDeletedException("An error occurred while updating the product with id " + productId, e);
        }
    }

    @Override
    public ProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not found"));

        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }
}
