package com.monolithic.FoodOrdering.orders.repository;

import com.monolithic.FoodOrdering.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order , Long> {
}
