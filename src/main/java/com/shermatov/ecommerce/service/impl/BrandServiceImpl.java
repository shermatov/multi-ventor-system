package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.Brand;
import com.shermatov.ecommerce.dto.request.BrandRequestDTO;
import com.shermatov.ecommerce.dto.response.BrandResponseDTO;
import com.shermatov.ecommerce.exception.ResourceNotFoundException;
import com.shermatov.ecommerce.mapper.BrandMapper;
import com.shermatov.ecommerce.repository.BrandRepository;
import com.shermatov.ecommerce.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {


    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandResponseDTO createBrand(BrandRequestDTO request) {

        if (brandRepository.existsByName(request.getName())) {
            throw new RuntimeException("Brand already exists");
        }

        Brand brand = brandMapper.toEntity(request);

        return brandMapper.toResponse(
                brandRepository.save(brand)
        );
    }

    @Override
    public BrandResponseDTO updateBrand(Long id, BrandRequestDTO request) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        brandMapper.updateEntity(brand, request);

        return brandMapper.toResponse(
                brandRepository.save(brand)
        );
    }

    @Override
    public void deleteBrand(Long id) {

        Brand category = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        brandRepository.delete(category);
    }

    @Override
    public BrandResponseDTO getBrandById(Long id) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        return brandMapper.toResponse(brand);
    }

    @Override
    public Page<BrandResponseDTO> getAllBrands(Pageable pageable) {

        return brandRepository.findAll(pageable)
                .map(brandMapper::toResponse);
    }
}
