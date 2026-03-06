package com.shermatov.ecommerce.service;

import com.shermatov.ecommerce.dto.request.CategoryRequestDTO;
import com.shermatov.ecommerce.dto.response.CategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryRequestDTO request);

    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request);

    void deleteCategory(Long id);

    CategoryResponseDTO getCategoryById(Long id);

    Page<CategoryResponseDTO> getAllCategories(Pageable pageable);

}