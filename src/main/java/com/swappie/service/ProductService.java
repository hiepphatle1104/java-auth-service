package com.swappie.service;

import com.swappie.domain.entities.Product;
import com.swappie.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {
    UUID createProduct(Product product);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(UUID id);

    void deleteProduct(UUID id);

    void updateProduct(UUID id, ProductDTO productDto);
}
