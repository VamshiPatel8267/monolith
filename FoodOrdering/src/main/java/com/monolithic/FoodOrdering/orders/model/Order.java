package com.monolithic.FoodOrdering.orders.model;

import com.monolithic.FoodOrdering.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderName;
    private double price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PLACED;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
