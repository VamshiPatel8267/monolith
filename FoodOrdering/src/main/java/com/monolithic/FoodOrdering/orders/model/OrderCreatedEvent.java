package com.monolithic.FoodOrdering.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderCreatedEvent {
    private long id;
    private String email;
    private String name;
    private String OrderName;
    private double price;
}
