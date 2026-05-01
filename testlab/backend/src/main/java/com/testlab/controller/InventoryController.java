package com.testlab.controller;

import com.testlab.dto.InventoryMovementRequest;
import com.testlab.dto.ProductResponse;
import com.testlab.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/in")
    public void stockIn(@Valid @RequestBody InventoryMovementRequest request) {
        inventoryService.increaseStock(request);
    }

    @PostMapping("/out")
    public void stockOut(@Valid @RequestBody InventoryMovementRequest request) {
        inventoryService.decreaseStock(request);
    }

    @GetMapping("/low-stock")
    public List<ProductResponse> getLowStock() {
        return inventoryService.getLowStockProducts();
    }
}
