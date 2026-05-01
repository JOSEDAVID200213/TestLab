package com.testlab.integration.controller;

import com.testlab.dto.ProductRequest;
import com.testlab.integration.BaseIntegrationTest;
import com.testlab.model.Category;
import com.testlab.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

class ProductControllerIT extends BaseIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void getAllProducts_returnsEmptyList_whenNoProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void createProduct_withValidData_returns201() throws Exception {
        Category category = categoryRepository.save(Category.builder().name("General").build());
        
        ProductRequest request = ProductRequest.builder()
            .name("Mouse")
            .price(new BigDecimal("25.50"))
            .stockQuantity(100)
            .minStockThreshold(10)
            .categoryId(category.getId())
            .build();

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Mouse"))
            .andExpect(jsonPath("$.categoryName").value("General"));
    }
}
