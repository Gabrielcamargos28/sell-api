package com.order.api.SellApi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequestDTO (
        @NotNull(message = "ID do produto não pode ser nulo")
        Long idProduto,
        @NotNull(message = "Quantidade não pode ser nula")
        @Min(value = 1, message = "A quantidade miníma deve ser 1")
        Integer quantidade
){
}
