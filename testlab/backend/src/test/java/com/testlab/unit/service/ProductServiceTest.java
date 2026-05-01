package com.testlab.unit.service;

import com.testlab.dto.ProductRequest;
import com.testlab.dto.ProductResponse;
import com.testlab.exception.ProductNotFoundException;
import com.testlab.mapper.ProductMapper;
import com.testlab.model.Category;
import com.testlab.model.Product;
import com.testlab.repository.CategoryRepository;
import com.testlab.repository.ProductRepository;
import com.testlab.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct_withValidData_returnsCreatedProduct() {
        // Arrange
        ProductRequest request = ProductRequest.builder()
            .name("Laptop")
            .price(new BigDecimal("1000"))
            .stockQuantity(10)
            .minStockThreshold(5)
            .categoryId(1L)
            .build();

        Category category = Category.builder().id(1L).name("Tech").build();
        Product product = Product.builder().name("Laptop").build();
        ProductResponse response = ProductResponse.builder().name("Laptop").build();

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.findByNameAndCategoryId("Laptop", 1L)).thenReturn(Optional.empty());
        when(productMapper.toEntity(request)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toResponse(product)).thenReturn(response);

        // Act
        ProductResponse result = productService.createProduct(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Laptop");
        verify(productRepository).save(any());
    }

    @Test
    void getProduct_withNonExistingId_throwsProductNotFoundException() {
        // Arrange
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> productService.getProductById(99L))
            .isInstanceOf(ProductNotFoundException.class)
            .hasMessageContaining("Product not found");
    }
}
