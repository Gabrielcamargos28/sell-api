package com.order.api.SellApi.dto.response;

public record OrderItemResponseDTO(
        Long id,
        Long idProduto,
        String nomeProduto,
        Integer quantidade,
        Double precoUnitario,
        Double precoTotal
){
}
