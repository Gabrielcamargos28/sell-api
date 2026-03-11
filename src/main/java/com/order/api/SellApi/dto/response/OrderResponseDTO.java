package com.order.api.SellApi.dto.response;

import com.order.api.SellApi.domain.Order;
import com.order.api.SellApi.domain.PayamentMethod;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderResponseDTO(
        Long id,
        String nome,
        String email,
        String endereco,
        PayamentMethod metodoDePagamento,
        Set<OrderItemResponseDTO> itens,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
    public OrderResponseDTO(Order order) {
        this(
                order.getId(),
                order.getName(),
                order.getEmail(),
                order.getAdress(),
                order.getPayamentMethod(),
                order.getItems() != null ? order.getItems().stream().map(OrderItemResponseDTO::new).collect(Collectors.toSet()) : null,
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
