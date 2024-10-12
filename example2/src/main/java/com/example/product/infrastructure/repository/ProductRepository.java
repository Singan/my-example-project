package com.example.product.infrastructure.repository;

import com.example.product.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
public interface ProductRepository {
    @Transactional
    void productRestock(long productId, int stock);

    void save(Product product);

    Product findById(long id);

    @Transactional
    void productDecrease(long id, int decrease);
}
