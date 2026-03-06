package com.shermatov.ecommerce.mapper;

import com.shermatov.ecommerce.domain.Shop;
import com.shermatov.ecommerce.dto.request.ShopRequestDTO;
import com.shermatov.ecommerce.dto.response.ShopResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ShopMapper {

    public Shop toEntity(ShopRequestDTO request) {

        Shop shop = new Shop();

        shop.setName(request.getName());
        shop.setDescription(request.getDescription());

        return shop;
    }

    public ShopResponseDTO toResponse(Shop shop) {

        return ShopResponseDTO.builder()
                .id(shop.getId())
                .name(shop.getName())
                .description(shop.getDescription())
                .ownerId(shop.getUser().getId())
                .build();
    }

    public void updateEntity(Shop shop, ShopRequestDTO request) {

        shop.setName(request.getName());
        shop.setDescription(request.getDescription());
    }
}