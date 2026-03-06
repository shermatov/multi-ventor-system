package com.shermatov.ecommerce.controller;

import com.shermatov.ecommerce.dto.request.ShopRequestDTO;
import com.shermatov.ecommerce.dto.response.ShopResponseDTO;
import com.shermatov.ecommerce.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<ShopResponseDTO> createShop(
            @Valid @RequestBody ShopRequestDTO request) {

        return ResponseEntity.status(201)
                .body(shopService.createShop(request));
    }

    @GetMapping
    public ResponseEntity<Page<ShopResponseDTO>> getAllShops(Pageable pageable) {

        return ResponseEntity.ok(
                shopService.getAllShops(pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopResponseDTO> getShop(@PathVariable Long id) {

        return ResponseEntity.ok(
                shopService.getShopById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopResponseDTO> updateShop(
            @PathVariable Long id,
            @Valid @RequestBody ShopRequestDTO request) {

        return ResponseEntity.ok(
                shopService.updateShop(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {

        shopService.deleteShop(id);
        return ResponseEntity.noContent().build();
    }
}