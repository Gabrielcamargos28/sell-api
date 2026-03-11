package com.order.api.SellApi.controller;

import com.order.api.SellApi.dto.request.ProductRequestDTO;
import com.order.api.SellApi.dto.response.ProductApiResponseDTO;
import com.order.api.SellApi.dto.response.ProductResponseDTO;
import com.order.api.SellApi.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ProductResponseDTO>> list(Pageable pageable) {
        return ResponseEntity.ok(productService.list(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductApiResponseDTO>> getApiProducts() {
        return ResponseEntity.ok(productService.getApiProducts());
    }

    @PostMapping("/fetch-api")
    public ResponseEntity<List<ProductResponseDTO>> fetchAndSaveApiProducts() {
        return ResponseEntity.ok(productService.fetchSaveApiProducts());
    }
}
