package com.example.product;

import com.example.product.domain.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.response.ProductDetailDto;
import com.example.product.util.ProductFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }


    public ProductDetailDto productDetail(Long id, Long cursor, PageRequest pageRequest) {
        Product product = productRepository.
                findByIdWithReviewsOrderByDescCreateDate(id, cursor, pageRequest);

        return ProductFactory.ofProductDetail(product, cursor);
    }


    public void productSave() {
        ProductEntity productEntity = ProductEntity.builder().reviewCount(0L).score(0f).build();
        productRepository.save(productEntity);
    }

    public void updateProductScore(long id , float score){
        productRepository.updateProductScore(id , score);
    }
}
