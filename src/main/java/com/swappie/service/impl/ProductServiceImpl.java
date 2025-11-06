package com.swappie.service.impl;

import com.swappie.domain.entities.Product;
import com.swappie.domain.entities.User;
import com.swappie.dto.ProductDTO;
import com.swappie.exception.NotFoundException;
import com.swappie.mapper.ProductMapper;
import com.swappie.repository.ProductRepository;
import com.swappie.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;
    private final ProductMapper mapper;

    @Override
    public UUID createProduct(ProductDTO dto, User user) {
        Product product = mapper.toEntity(dto);
        product.setSeller(user);

        Product saved = repo.save(product);
        return saved.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        List<Product> products = repo.findAll();
        if (products.isEmpty())
            throw new NotFoundException("products not found", "PRODUCTS_NOT_FOUND");

        return products.stream().map(mapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO getProductById(UUID id) {
        Optional<Product> result = repo.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("product not found", "PRODUCT_NOT_FOUND");

        return mapper.toDTO(result.get());
    }

    @Override
    public void deleteProduct(UUID id) {
        if (!repo.existsById(id))
            throw new NotFoundException("product not found", "PRODUCT_NOT_FOUND");

        repo.deleteById(id);
    }

    @Override
    @Transactional
    public void updateProduct(UUID id, ProductDTO productDto) {
        Optional<Product> result = repo.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("product not found", "PRODUCT_NOT_FOUND");

        Product exist = result.get();
        mapper.updateEntityFromDTO(productDto, exist);
        repo.save(exist);
    }
}
