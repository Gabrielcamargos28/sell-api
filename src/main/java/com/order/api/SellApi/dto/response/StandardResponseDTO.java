package com.order.api.SellApi.dto.response;

import java.time.LocalDateTime;

public record StandardResponseDTO<T>(
        int status,
        String path,
        String duration,
        LocalDateTime timestamp,
        T data
) {
}
