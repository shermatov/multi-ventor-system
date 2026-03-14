package com.shermatov.ecommerce.controller;

import com.shermatov.ecommerce.dto.request.BrandRequestDTO;
import com.shermatov.ecommerce.dto.response.BrandResponseDTO;
import com.shermatov.ecommerce.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandResponseDTO> createBrand(
            @Valid @RequestBody BrandRequestDTO request) {

        return ResponseEntity.status(201)
                .body(brandService.createBrand(request));
    }

    @GetMapping
    public ResponseEntity<Page<BrandResponseDTO>> getAllBrands(Pageable pageable) {

        return ResponseEntity.ok(
                brandService.getAllBrands(pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> getBrand(@PathVariable Long id) {

        return ResponseEntity.ok(
                brandService.getBrandById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> updateBrand(
            @PathVariable Long id,
            @Valid @RequestBody BrandRequestDTO request) {

        return ResponseEntity.ok(
                brandService.updateBrand(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {

        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
