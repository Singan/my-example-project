package com.example.product;

import com.example.product.response.ProductDetailDto;
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
    public ProductDetailDto productDetail(@PathVariable Long productId, @RequestParam Long cursor,
                                          @RequestParam Integer size) throws IOException {

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
