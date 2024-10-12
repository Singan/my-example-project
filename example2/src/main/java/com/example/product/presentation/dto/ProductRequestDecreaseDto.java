package com.example.product.presentation.dto;

import com.example.product.application.dto.request.ProductDecreaseDto;

public record ProductRequestDecreaseDto(Long productId, int decreaseStock) {

    public ProductDecreaseDto getProductDecreaseDto() {
        return new ProductDecreaseDto(productId, decreaseStock);
    }
}
