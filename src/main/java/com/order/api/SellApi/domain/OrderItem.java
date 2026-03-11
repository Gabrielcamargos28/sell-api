package com.order.api.SellApi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double uniPrice;
    @Column(nullable = false)
    private Double totalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(nullable = false)
    private Timestamp created_at;
    @Column(nullable = false)
    private Timestamp update_at;

    @PrePersist
    @PreUpdate
    public void calculateTotal(){
        this.totalPrice = uniPrice * quantity;
    }
}
