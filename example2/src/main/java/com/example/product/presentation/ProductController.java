package com.example.product.presentation;

import com.example.product.application.ProductService;
import com.example.product.application.dto.request.ProductDecreaseDto;
import com.example.product.presentation.dto.ProductRequestDecreaseDto;
import com.example.product.presentation.dto.ProductRestockRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public void saveProduct() {
        productService.productSave();
    }

    @PutMapping()
    public void productDecrease(ProductRequestDecreaseDto requestDecreaseDto) {
        productService.productDecrease(requestDecreaseDto.getProductDecreaseDto());
    }

    @PostMapping("/{productId}/notifications/re-stock")
    public void productRestock(@PathVariable Long productId , ProductRestockRequestDto requestDto) {
        productService.productRestock(productId , requestDto.increaseStock());
    }
}
