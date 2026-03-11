package com.order.api.SellApi.dto.response;

import java.sql.Timestamp;

public record ProductResponseDTO(
        Long id,
        String nome,
        Double preco,
        String descricao,
        String estoque,
        String caminhoImagem,
        Timestamp created_at,
        Timestamp updated_at
) {
}
