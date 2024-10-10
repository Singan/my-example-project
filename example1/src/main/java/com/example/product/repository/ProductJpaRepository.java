package com.example.product.repository;

import com.example.product.ProductEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProductEntity p where p.id = :id ")
    ProductEntity findByIdLock(@Param("id") Long id);


    @Modifying
    @Query("UPDATE ProductEntity p SET p.score = ((p.score * p.reviewCount) + :score) / (p.reviewCount + 1), " +
            "p.reviewCount = p.reviewCount + 1 WHERE p.id = :id")
    @Transactional
    void updateProductEntityById(@Param("id") long id , @Param("score")float score);
}
