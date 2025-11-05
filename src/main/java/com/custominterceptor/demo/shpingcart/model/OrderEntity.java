package com.custominterceptor.demo.shpingcart.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

}
