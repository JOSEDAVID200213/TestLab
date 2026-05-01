package com.testlab.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
    private Integer minStockThreshold;
    private Long categoryId;
    private String categoryName;
}
