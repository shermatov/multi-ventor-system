package com.shermatov.ecommerce.service;

import com.shermatov.ecommerce.dto.request.ShopRequestDTO;
import com.shermatov.ecommerce.dto.response.ShopResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopService {

    ShopResponseDTO createShop(ShopRequestDTO request);

    ShopResponseDTO updateShop(Long id, ShopRequestDTO request);

    void deleteShop(Long id);

    ShopResponseDTO getShopById(Long id);

    Page<ShopResponseDTO> getAllShops(Pageable pageable);
}