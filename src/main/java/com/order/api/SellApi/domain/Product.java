package com.order.api.SellApi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="tb_product")
public class Product {
    @Id
    private Long id;
    @Column(nullable = false)
    private String tittle;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(nullable = false)
    private Integer stock;
    private String imagePath;
    @Column(nullable = false)
    private Timestamp created_at;
    @Column(nullable = false)
    private Timestamp update_at;
}
