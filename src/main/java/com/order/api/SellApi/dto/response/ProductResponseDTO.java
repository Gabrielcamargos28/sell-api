package com.order.api.SellApi.dto.response;

import com.order.api.SellApi.domain.Product;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String nome,
        Double preco,
        String descricao,
        Integer estoque,
        String caminhoImagem,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
    public ProductResponseDTO(Product product){
        this(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getDescription(),
                product.getStock(),
                product.getImagePath(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
