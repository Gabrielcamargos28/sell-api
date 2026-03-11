package com.order.api.SellApi.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String adress;
    @Column(nullable = false)
    private PayamentMethod  payamentMethod;
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items;
    @Column(nullable = false)
    private Timestamp created_at;
    @Column(nullable = false)
    private Timestamp update_at;
}
