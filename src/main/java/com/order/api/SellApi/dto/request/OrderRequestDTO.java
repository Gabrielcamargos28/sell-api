package com.order.api.SellApi.dto.request;

import com.order.api.SellApi.domain.PayamentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record OrderRequestDTO(
        @NotNull(message = "Nome não pode ser nulo")
        @NotBlank(message = "Nome não pode ser vazio")
        String nome,
        @NotNull(message = "Email não pode ser nulo")
        @NotBlank(message = "Email não pode ser vazio")
        String email,
        @NotNull(message = "Endereço não pode ser nulo")
        @NotBlank(message = "Endereço não pode ser vazio")
        String endereco,
        @NotNull(message = "Método de pagamento não pode ser nulo")
        @NotBlank(message = "Método de pagamento não pode ser vazio")
        PayamentMethod formaDePagament,
        @NotNull(message = "Itens não pode ser nulo")
        @NotBlank(message = "Itens não pode ser vazio")
        Set<OrderItemRequestDTO> itens
) {
}
