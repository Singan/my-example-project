package com.example.product;

import com.example.product.response.ProductDetailDto;
import com.example.product.response.ProductWithReview;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/products/{productId}/reviews")
    public ProductWithReview productDetail(@PathVariable Long productId, @RequestParam Long cursor,
                                           @Valid @RequestParam @Min(1) Integer size) throws IOException {

        return productService.productDetail(
                productId,
                cursor,
                PageRequest.ofSize(size));
    }

    @PostMapping("/products")
    public void productInsert() {
        productService.productSave();
    }

}
