package com.shermatov.ecommerce.mapper;

import com.shermatov.ecommerce.domain.Product;
import com.shermatov.ecommerce.domain.ProductImage;
import com.shermatov.ecommerce.dto.request.ProductRequestDTO;
import com.shermatov.ecommerce.dto.response.ProductResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO request) {
        if (request == null) return null;

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());


        return product;
    }

    public ProductResponseDTO toResponse(Product product) {

        if (product == null) return null;

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryId(getCategoryId(product))
                .brandId(getBrandId(product))
                .shopId(getShopId(product))
                .imageUrls(getImageUrls(product))
                .averageRating(product.getAverageRating())
                .reviewCount(product.getReviewCount())
                .build();
    }

    public void updateEntity(Product product, ProductRequestDTO request) {

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
    }

    private Long getCategoryId(Product product) {
        return product.getCategory() != null ? product.getCategory().getId() : null;
    }

    private Long getBrandId(Product product) {
        return product.getBrand() != null ? product.getBrand().getId() : null;
    }

    private Long getShopId(Product product) {
        return product.getShop() != null ? product.getShop().getId() : null;
    }

    private List<String> getImageUrls(Product product) {

        if (product.getImages() == null) return List.of();

        return product.getImages()
                .stream()
                .map(ProductImage::getImageUrl)
                .toList();
    }
}