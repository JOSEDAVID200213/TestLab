package com.testlab.mapper;

import com.testlab.dto.ProductRequest;
import com.testlab.dto.ProductResponse;
import com.testlab.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Product toEntity(ProductRequest request);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "stockQuantity", ignore = true)
    void updateEntity(ProductRequest request, @MappingTarget Product product);
}
