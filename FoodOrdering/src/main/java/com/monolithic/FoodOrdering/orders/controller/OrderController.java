package com.monolithic.FoodOrdering.orders.controller;

import com.monolithic.FoodOrdering.orders.model.Order;
import com.monolithic.FoodOrdering.orders.model.OrderStatus;
import com.monolithic.FoodOrdering.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
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

//    @PutMapping("/update/{id}/{status}")
//    public String update(@PathVariable long id , @PathVariable OrderStatus status){
//        orderService.updateStatus(id , status);
//        return "your order is updated to "+ status;
//    }

    @GetMapping("/getOrdersById/{id}")
    public List<Order> getbyId(@PathVariable long id){
        List<Order> orders = orderService.getOrdersById(id);
        return orders;
    }

}
