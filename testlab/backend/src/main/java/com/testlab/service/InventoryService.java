package com.testlab.service;

import com.testlab.dto.InventoryMovementRequest;
import com.testlab.dto.ProductResponse;
import com.testlab.exception.InsufficientStockException;
import com.testlab.exception.ProductNotFoundException;
import com.testlab.mapper.ProductMapper;
import com.testlab.model.InventoryMovement;
import com.testlab.model.Product;
import com.testlab.repository.InventoryMovementRepository;
import com.testlab.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final ProductRepository productRepository;
    private final InventoryMovementRepository movementRepository;
    private final ProductMapper productMapper;

    @Transactional
    public void increaseStock(InventoryMovementRequest request) {
        Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new ProductNotFoundException("Product not found: " + request.getProductId()));

        product.setStockQuantity(product.getStockQuantity() + request.getQuantity());
        productRepository.save(product);

        InventoryMovement movement = InventoryMovement.builder()
            .product(product)
            .quantity(request.getQuantity())
            .type(InventoryMovement.MovementType.IN)
            .timestamp(LocalDateTime.now())
            .build();
        movementRepository.save(movement);
    }

    @Transactional
    public void decreaseStock(InventoryMovementRequest request) {
        Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new ProductNotFoundException("Product not found: " + request.getProductId()));

        if (product.getStockQuantity() < request.getQuantity()) {
            throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
        }

        product.setStockQuantity(product.getStockQuantity() - request.getQuantity());
        productRepository.save(product);

        InventoryMovement movement = InventoryMovement.builder()
            .product(product)
            .quantity(request.getQuantity())
            .type(InventoryMovement.MovementType.OUT)
            .timestamp(LocalDateTime.now())
            .build();
        movementRepository.save(movement);
    }

    public List<ProductResponse> getLowStockProducts() {
        return productRepository.findLowStockProducts().stream()
            .map(productMapper::toResponse)
            .collect(Collectors.toList());
    }

    public List<InventoryMovement> getMovementHistory(Long productId) {
        return movementRepository.findByProductIdOrderByTimestampDesc(productId);
    }
}
