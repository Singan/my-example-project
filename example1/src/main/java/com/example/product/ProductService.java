package com.example.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public boolean productExists(Long id){
        return productRepository.existsById(id);
    }
}
