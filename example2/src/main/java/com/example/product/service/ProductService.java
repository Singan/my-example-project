package com.example.product.service;

import com.example.product.service.dto.request.ProductDecreaseDto;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
    @Transactional
    void productSave();

    int productRestock(long id, int stock);

    @Transactional
    void productDecrease(ProductDecreaseDto productDecreaseDto);
}
