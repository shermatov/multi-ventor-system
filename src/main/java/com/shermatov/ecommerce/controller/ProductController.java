package com.shermatov.ecommerce.controller;

import com.shermatov.ecommerce.dto.request.ProductRequestDTO;
import com.shermatov.ecommerce.dto.response.ProductResponseDTO;
import com.shermatov.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create a product for a shop
    @PostMapping("/shop/{shopId}")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @PathVariable Long shopId,
            @Valid @RequestBody ProductRequestDTO request) {
        return ResponseEntity.status(201)
                .body(productService.createProduct(shopId, request));
    }

    // Update a product
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequestDTO request) {
        return ResponseEntity.ok(productService.updateProduct(productId, request));
    }

    // Delete a product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    // Get all products
    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    // Get products by shop
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByShop(@PathVariable Long shopId) {
        return ResponseEntity.ok(productService.getProductsByShop(shopId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(
            @PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}