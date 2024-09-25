package org.alten.core.repository;

import lombok.extern.slf4j.Slf4j;
import org.alten.core.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Product_Test");
        product.setPrice(150.0);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Product_Test", savedProduct.getName());
    }

    @Test
    public void testFindAllProducts() {
        Product product1 = new Product();
        product1.setName("Product X");
        product1.setPrice(50.0);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Product Y");
        product2.setPrice(150.0);
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Product Z");
        product3.setPrice(300.0);
        productRepository.save(product3);

        List<Product> productList = productRepository.findAll();

        assertEquals(3, productList.size());

        assertTrue(productList.contains(product1));
        assertTrue(productList.contains(product2));
        assertTrue(productList.contains(product3));
    }

    @Test
    public void testFindProductById() {
        Product product = new Product();
        product.setName("Product_Test");
        product.setPrice(150.0);

        Product savedProduct = productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals(savedProduct.getId(), foundProduct.get().getId());
        assertEquals("Product_Test", foundProduct.get().getName());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setName("ProductX");
        product.setPrice(100.0);
        product = productRepository.save(product);

        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        assertTrue(optionalProduct.isPresent());
        Product existingProduct = optionalProduct.get();

        existingProduct.setName("ProductY");
        productRepository.save(existingProduct);

        Optional<Product> updatedProductOptional = productRepository.findById(existingProduct.getId());
        assertTrue(updatedProductOptional.isPresent());
        Product updatedProduct = updatedProductOptional.get();

        assertEquals("ProductY", updatedProduct.getName());
        assertEquals(100.0, updatedProduct.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setName("Product_Test");
        product.setPrice(150.0);

        Product savedProduct = productRepository.save(product);

        productRepository.deleteById(savedProduct.getId());

        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getId());
        assertFalse(deletedProduct.isPresent());
    }
}
