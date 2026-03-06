package com.shermatov.ecommerce.mapper;


import com.shermatov.ecommerce.domain.Category;
import com.shermatov.ecommerce.dto.request.CategoryRequestDTO;
import com.shermatov.ecommerce.dto.response.CategoryResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDTO request) {

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return category;
    }

    public CategoryResponseDTO toResponse(Category category) {

        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public void updateEntity(Category category, CategoryRequestDTO request) {

        category.setName(request.getName());
        category.setDescription(request.getDescription());
    }
}
