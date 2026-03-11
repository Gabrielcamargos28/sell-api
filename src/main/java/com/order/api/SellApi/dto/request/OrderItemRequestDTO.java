package com.order.api.SellApi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequestDTO (
        @NotNull(message = "Não pode ser nulo")
        @NotBlank(message = "Não pode ser vazio")
        Long idProduto,
        @NotNull(message = "Não pode ser nula")
        @NotBlank(message = "Não pode ser vazia")
        @Min(value = 1, message = "A quantidade miníma deve ser  1")
        Integer quantidade
){
}
