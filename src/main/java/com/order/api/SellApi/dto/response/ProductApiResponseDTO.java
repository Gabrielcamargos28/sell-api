package com.order.api.SellApi.dto.response;

import java.sql.Timestamp;

public record ProductApiResponseDTO(
        Long id,
        String title,
        Double price,
        String description,
        String image
) {
}
