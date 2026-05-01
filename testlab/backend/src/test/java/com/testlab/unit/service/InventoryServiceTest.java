package com.testlab.unit.service;

import com.testlab.dto.InventoryMovementRequest;
import com.testlab.exception.InsufficientStockException;
import com.testlab.model.Product;
import com.testlab.repository.InventoryMovementRepository;
import com.testlab.repository.ProductRepository;
import com.testlab.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private InventoryMovementRepository movementRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void decreaseStock_withSufficientStock_updatesQuantity() {
        // Arrange
        InventoryMovementRequest request = new InventoryMovementRequest(1L, 5);
        Product product = Product.builder().id(1L).stockQuantity(10).name("Test").build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        inventoryService.decreaseStock(request);

        // Assert
        assertThat(product.getStockQuantity()).isEqualTo(5);
        verify(productRepository).save(product);
        verify(movementRepository).save(any());
    }

    @Test
    void decreaseStock_withInsufficientStock_throwsInsufficientStockException() {
        // Arrange
        InventoryMovementRequest request = new InventoryMovementRequest(1L, 15);
        Product product = Product.builder().id(1L).stockQuantity(10).name("Test").build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act & Assert
        assertThatThrownBy(() -> inventoryService.decreaseStock(request))
            .isInstanceOf(InsufficientStockException.class)
            .hasMessageContaining("Insufficient stock");
    }
}
