package com.monolithic.FoodOrdering.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monolithic.FoodOrdering.orders.model.Order;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String name;
    private String email;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;
}
