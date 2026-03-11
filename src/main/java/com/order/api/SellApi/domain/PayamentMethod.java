package com.order.api.SellApi.domain;

import lombok.Getter;

@Getter
public enum PayamentMethod {
    PIX("pix"),
    CARD("cartão"),
    BILL("boleto");

    private final String description;
    PayamentMethod(String description){this.description = description;}
}
