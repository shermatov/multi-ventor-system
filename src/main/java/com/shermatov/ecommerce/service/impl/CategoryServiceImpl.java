package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.Category;
import com.shermatov.ecommerce.dto.request.CategoryRequestDTO;
import com.shermatov.ecommerce.dto.response.CategoryResponseDTO;
import com.shermatov.ecommerce.exception.ResourceNotFoundException;
import com.shermatov.ecommerce.mapper.CategoryMapper;
import com.shermatov.ecommerce.repository.CategoryRepository;
import com.shermatov.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {

        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category already exists");
        }

        Category category = categoryMapper.toEntity(request);

        return categoryMapper.toResponse(
                categoryRepository.save(category)
        );
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        categoryMapper.updateEntity(category, request);

        return categoryMapper.toResponse(
                categoryRepository.save(category)
        );
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        return categoryMapper.toResponse(category);
    }

    @Override
    public Page<CategoryResponseDTO> getAllCategories(Pageable pageable) {

        return categoryRepository.findAll(pageable)
                .map(categoryMapper::toResponse);
    }
}