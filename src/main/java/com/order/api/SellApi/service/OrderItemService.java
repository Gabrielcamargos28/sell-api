package com.order.api.SellApi.service;

import com.order.api.SellApi.domain.OrderItem;
import com.order.api.SellApi.domain.Product;
import com.order.api.SellApi.repository.OrderItemRepository;
import com.order.api.SellApi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderItem createOrderItem(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for product: " + product.getTitle());
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        return OrderItem.builder()
                .productId(product.getId())
                .productName(product.getTitle())
                .uniPrice(product.getPrice())
                .quantity(quantity)
                .build();
    }
}
