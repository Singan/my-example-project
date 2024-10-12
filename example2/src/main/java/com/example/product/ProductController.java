package com.example.product;

import com.example.product.application.ProductService;
import com.example.product.application.dto.request.ProductDecreaseDto;
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
    public void productDecrease(ProductDecreaseDto productDecreaseDto) {
        productService.productSave();
    }

    @PostMapping("/{productId}/notifications/re-stock")
    public void productRestock(@PathVariable Long productId) {


    }
}
