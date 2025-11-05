package com.swappie.service;

import com.swappie.domain.entities.User;
import com.swappie.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {
    UUID createProduct(ProductDTO dto, User user);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(UUID id);

    void deleteProduct(UUID id);

    void updateProduct(UUID id, ProductDTO productDto);
}
