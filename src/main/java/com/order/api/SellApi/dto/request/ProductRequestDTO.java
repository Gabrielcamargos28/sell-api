package com.order.api.SellApi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
        @NotNull(message = "Id não pode ser nulo")
        Long id,
        @NotNull(message = "Nome não pode ser nulo")
        @NotBlank(message = "Nome não pode ser vazio")
        String nome,
        @NotNull(message = "Descricao não pode ser nulo")
        @NotBlank(message = "Descricao não pode ser vazio")
        String descricao,
        @NotNull(message = "Preço não pode ser nulo")
        Double preco,
        @NotNull(message = "Estoque não pode ser nulo")
        Integer estoque,
        String pathImage
) {
}
