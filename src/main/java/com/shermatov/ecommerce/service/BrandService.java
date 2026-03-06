package com.shermatov.ecommerce.service;

import com.shermatov.ecommerce.dto.request.BrandRequestDTO;
import com.shermatov.ecommerce.dto.response.BrandResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {

    BrandResponseDTO createBrand(BrandRequestDTO request);

    BrandResponseDTO updateBrand(Long id, BrandRequestDTO request);

    void deleteBrand(Long id);

    BrandResponseDTO getBrandById(Long id);

    Page<BrandResponseDTO> getAllBrands(Pageable pageable);

}
