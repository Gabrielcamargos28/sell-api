package com.order.api.SellApi.dto.response;

import com.order.api.SellApi.domain.OrderItem;

public record OrderItemResponseDTO(
        Long id,
        Long idProduto,
        String nomeProduto,
        Integer quantidade,
        Double precoUnitario,
        Double precoTotal
){
    public OrderItemResponseDTO(OrderItem item) {
        this(
                item.getId(),
                item.getProductId(),
                item.getProductName(),
                item.getQuantity(),
                item.getUniPrice(),
                item.getTotalPrice()
        );
    }
}
