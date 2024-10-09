package com.example.product.repository;

import com.example.product.ProductEntity;
import com.example.product.domain.Product;
import org.springframework.data.domain.PageRequest;

public interface ProductRepository {


    boolean existsById(Long id);

    Product findByIdWithReviewsOrderByDescCreateDate(Long id , Long cursor, PageRequest pageRequest);


    void save(ProductEntity productEntity);
}
