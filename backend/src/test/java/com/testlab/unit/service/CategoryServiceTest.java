package com.testlab.unit.service;

import com.testlab.model.Category;
import com.testlab.repository.CategoryRepository;
import com.testlab.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void createCategory_withValidData_returnsCreatedCategory() {
        // Arrange
        String name = "Electronics";
        Category category = Category.builder().name(name).build();
        when(categoryRepository.findByName(name)).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // Act
        Category result = categoryService.createCategory(name);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void createCategory_withDuplicateName_throwsException() {
        // Arrange
        String name = "Electronics";
        when(categoryRepository.findByName(name)).thenReturn(Optional.of(new Category()));

        // Act & Assert
        assertThatThrownBy(() -> categoryService.createCategory(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Category already exists");
    }
}
