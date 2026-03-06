package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.Brand;
import com.shermatov.ecommerce.domain.Category;
import com.shermatov.ecommerce.domain.Product;
import com.shermatov.ecommerce.domain.Shop;
import com.shermatov.ecommerce.dto.request.ProductRequestDTO;
import com.shermatov.ecommerce.dto.response.ProductResponseDTO;
import com.shermatov.ecommerce.exception.ResourceNotFoundException;
import com.shermatov.ecommerce.mapper.ProductMapper;
import com.shermatov.ecommerce.repository.BrandRepository;
import com.shermatov.ecommerce.repository.CategoryRepository;
import com.shermatov.ecommerce.repository.ProductRepository;
import com.shermatov.ecommerce.repository.ShopRepository;
import com.shermatov.ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public ProductResponseDTO createProduct(Long shopId, ProductRequestDTO request) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        Product product = productMapper.toEntity(request);
        product.setShop(shop);
        product.setCategory(category);
        product.setBrand(brand);

        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productMapper.updateEntity(product, request);

        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productRepository.delete(product);
    }

    @Override
    public ProductResponseDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponseDTO> getProductsByShop(Long shopId) {
        return productRepository.findByShopId(shopId)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {

        return productRepository.findAll(pageable)
                .map(productMapper::toResponse);
    }
}