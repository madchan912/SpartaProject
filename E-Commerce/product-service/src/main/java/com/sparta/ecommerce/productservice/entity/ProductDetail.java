package com.sparta.ecommerce.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
    @Id
    private Long productId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    private String detailTitle;
    private String detailPicture;
    private int quantity;
}
