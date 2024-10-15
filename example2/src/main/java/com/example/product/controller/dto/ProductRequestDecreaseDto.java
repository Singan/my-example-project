package com.example.product.controller.dto;

import com.example.product.service.dto.request.ProductDecreaseDto;

public record ProductRequestDecreaseDto(Long productId, int decreaseStock) {

    public ProductDecreaseDto getProductDecreaseDto() {
        return new ProductDecreaseDto(productId, decreaseStock);
    }
}
