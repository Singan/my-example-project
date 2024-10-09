package com.example.review;

import com.example.product.ProductService;
import com.example.review.request.ReviewInsertDto;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping("/products/{productId}/reviews")
    public void reviewInsert(@PathVariable Long productId, MultipartFile image,
                             @RequestBody @Valid ReviewInsertDto reviewInsertDto) throws IOException {

        reviewService.reviewInsert(
                reviewInsertDto.getReviewWithProduct(productId)
        );
    }
}
