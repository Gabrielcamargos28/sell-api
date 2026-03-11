package com.order.api.SellApi.dto.response;

import java.sql.Timestamp;

public record ProductApi1ResponseDTO(
        Long id,
        String tittle,
        Double price,
        String description,
        String image,
        Timestamp created_at,
        Timestamp updated_at
) {
}
