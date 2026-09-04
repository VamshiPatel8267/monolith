package com.monolithic.FoodOrdering.orders.controller;

import com.monolithic.FoodOrdering.orders.model.Order;
import com.monolithic.FoodOrdering.orders.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get")
    public List<Order>  getAll(){
      List<Order> order =  orderService.getall();
      return order;
    }

    @PostMapping("/place")
    public Order place(@RequestBody Order order){
        return orderService.placeOrder(order);
    }
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable long id){
        return orderService.delete(id);
    }

}
