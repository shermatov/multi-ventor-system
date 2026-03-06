package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.Shop;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.ShopRequestDTO;
import com.shermatov.ecommerce.dto.response.ShopResponseDTO;
import com.shermatov.ecommerce.exception.ResourceNotFoundException;
import com.shermatov.ecommerce.mapper.ShopMapper;
import com.shermatov.ecommerce.repository.ShopRepository;
import com.shermatov.ecommerce.repository.UserRepository;
import com.shermatov.ecommerce.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final ShopMapper shopMapper;

    @Override
    public ShopResponseDTO createShop(ShopRequestDTO request) {

        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Shop shop = shopMapper.toEntity(request);
        shop.setUser(owner);

        return shopMapper.toResponse(
                shopRepository.save(shop)
        );
    }

    @Override
    public ShopResponseDTO updateShop(Long id, ShopRequestDTO request) {

        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        shopMapper.updateEntity(shop, request);

        return shopMapper.toResponse(
                shopRepository.save(shop)
        );
    }

    @Override
    public void deleteShop(Long id) {

        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        shopRepository.delete(shop);
    }

    @Override
    public ShopResponseDTO getShopById(Long id) {

        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        return shopMapper.toResponse(shop);
    }

    @Override
    public Page<ShopResponseDTO> getAllShops(Pageable pageable) {

        return shopRepository.findAll(pageable)
                .map(shopMapper::toResponse);
    }
}