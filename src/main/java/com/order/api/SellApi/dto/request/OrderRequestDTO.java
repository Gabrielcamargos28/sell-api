package com.order.api.SellApi.dto.request;

import com.order.api.SellApi.domain.PayamentMethod;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record OrderRequestDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        String nome,
        @NotBlank(message = "Email não pode ser vazio")
        @Email(message = "Email deve ser válido")
        String email,
        @NotBlank(message = "Endereço não pode ser vazio")
        String endereco,
        @NotNull(message = "Método de pagamento não pode ser nulo")
        PayamentMethod formaDePagamento,
        @NotEmpty(message = "Itens não pode ser vazio")
        Set<OrderItemRequestDTO> itens
) {
}
