package com.testlab.service;

import com.testlab.dto.ProductRequest;
import com.testlab.dto.ProductResponse;
import com.testlab.exception.ProductNotFoundException;
import com.testlab.mapper.ProductMapper;
import com.testlab.model.Category;
import com.testlab.model.Product;
import com.testlab.repository.CategoryRepository;
import com.testlab.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
            .map(productMapper::toResponse)
            .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
            .map(productMapper::toResponse)
            .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new IllegalArgumentException("Category not found: " + request.getCategoryId()));

        if (productRepository.findByNameAndCategoryId(request.getName(), category.getId()).isPresent()) {
            throw new IllegalArgumentException("Product name must be unique within a category");
        }

        Product product = productMapper.toEntity(request);
        product.setCategory(category);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));

        Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new IllegalArgumentException("Category not found: " + request.getCategoryId()));

        productMapper.updateEntity(request, product);
        product.setCategory(category);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
        productRepository.delete(product);
    }

    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
            .map(productMapper::toResponse)
            .collect(Collectors.toList());
    }
}
