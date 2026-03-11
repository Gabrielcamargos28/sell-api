package com.order.api.SellApi.dto.response;

import com.order.api.SellApi.domain.PayamentMethod;

import java.sql.Timestamp;
import java.util.Set;

public record OrderResponseDTO(
        Long id,
        String nome,
        String email,
        String endereco,
        PayamentMethod metodoDePagamento,
        Set<OrderItemResponseDTO> itens,
        Timestamp created_at,
        Timestamp updated_at
) {
}
