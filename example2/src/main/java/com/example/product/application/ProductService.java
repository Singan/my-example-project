package com.example.product.application;

import com.example.product.application.dto.request.ProductDecreaseDto;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
    @Transactional
    void productSave();

    void productRestock(long id, int stock);

    @Transactional
    void productDecrease(ProductDecreaseDto productDecreaseDto);
}
