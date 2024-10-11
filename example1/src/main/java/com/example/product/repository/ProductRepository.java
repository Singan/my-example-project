package com.example.product.repository;

import com.example.product.ProductEntity;
import com.example.product.domain.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository {


    boolean existsById(Long id);

    Product findByIdWithReviewsOrderByDescCreateDate(Long id);


    long save(ProductEntity productEntity);

    void updateProductScore(long id,float score);
}
