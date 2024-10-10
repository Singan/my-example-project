package com.example.review;

import com.example.file.S3Service;
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
    private S3Service s3Service;

    @PostMapping("/products/{productId}/reviews")
    public void reviewInsert(@PathVariable Long productId,
                             @Valid ReviewInsertDto reviewInsertDto) throws IOException {

        if (!(reviewInsertDto.image() == null || reviewInsertDto.image().isEmpty())) {
            String path = s3Service.save(reviewInsertDto.image(), "review", "img");
            reviewService.reviewInsert(
                    reviewInsertDto.getReview(productId, path));
        } else {
            reviewService.reviewInsert(
                    reviewInsertDto.getReview(productId));
        }
    }
}
