package com.shermatov.ecommerce.mapper;


import com.shermatov.ecommerce.domain.Brand;

import com.shermatov.ecommerce.dto.request.BrandRequestDTO;
import com.shermatov.ecommerce.dto.response.BrandResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public Brand toEntity(BrandRequestDTO request) {

        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());

        return brand;
    }

    public BrandResponseDTO toResponse(Brand brand) {

        return BrandResponseDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .build();
    }

    public void updateEntity(Brand brand, BrandRequestDTO request) {

        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
    }
}
