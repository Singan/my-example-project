package com.example.review;

import com.example.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    boolean existsByUserId(Long userId);
}
