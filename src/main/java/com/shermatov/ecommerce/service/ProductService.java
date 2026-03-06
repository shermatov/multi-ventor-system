package com.shermatov.ecommerce.service;

import com.shermatov.ecommerce.dto.request.ProductRequestDTO;
import com.shermatov.ecommerce.dto.response.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(Long shopId, ProductRequestDTO request);

    ProductResponseDTO updateProduct(Long productId, ProductRequestDTO request);

    void deleteProduct(Long productId);

    ProductResponseDTO getProductById(Long productId);

    List<ProductResponseDTO> getProductsByShop(Long shopId);

    Page<ProductResponseDTO> getAllProducts(Pageable pageable);
}