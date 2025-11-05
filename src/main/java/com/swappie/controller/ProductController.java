package com.swappie.controller;

import com.swappie.dto.ProductDTO;
import com.swappie.mapper.ProductMapper;
import com.swappie.service.ProductService;
import com.swappie.utils.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        var products = productService.getAllProducts();

        var res = ResponseFactory.ok("all products fetched", products);
        return new ResponseEntity<>(res, res.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id) {
        var product = productService.getProductById(id);

        var res = ResponseFactory.ok("product fetched", product);
        return new ResponseEntity<>(res, res.getStatus());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO request) {
        var productId = productService.createProduct(mapper.toEntity(request));

        var res = ResponseFactory.created("product created", productId);
        return new ResponseEntity<>(res, res.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);

        var res = ResponseFactory.ok("product deleted");
        return new ResponseEntity<>(res, res.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO request) {
        productService.updateProduct(id, request);

        var res = ResponseFactory.ok("product updated");
        return new ResponseEntity<>(res, res.getStatus());
    }
}
