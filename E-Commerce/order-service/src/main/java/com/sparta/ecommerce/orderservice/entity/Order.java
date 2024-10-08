package com.sparta.ecommerce.orderservice.entity;

import com.sparta.ecommerce.userservice.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime date;
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<com.sparta.ecommerce.orderservice.entity.OrderItem> orderItems;
}
