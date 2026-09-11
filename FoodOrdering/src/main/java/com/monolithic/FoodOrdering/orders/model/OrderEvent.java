package com.monolithic.FoodOrdering.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private long id;
    private String name;
    private String email;
    private String orderName;
    private double price;
}
