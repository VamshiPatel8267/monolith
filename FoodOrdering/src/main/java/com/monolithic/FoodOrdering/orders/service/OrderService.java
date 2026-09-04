package com.monolithic.FoodOrdering.orders.service;

import com.monolithic.FoodOrdering.orders.model.Order;
import com.monolithic.FoodOrdering.orders.repository.OrderRepository;
import com.monolithic.FoodOrdering.user.model.User;
import com.monolithic.FoodOrdering.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Order placeOrder(Order order) {

        Long userID = order.getUser().getId(); // getting userId from order
        User user_details = userRepository.findById(userID).orElseThrow(()->new RuntimeException("User Not Found"));
        order.setUser(user_details);



        return orderRepository.save(order);
    }

    public boolean delete(long input){
        if(orderRepository.findById(input).isPresent()){
            orderRepository.deleteById(input);
            return true;
        }
        return false;
    }
    public List<Order> getall(){
       return orderRepository.findAll();
    }
    public Optional<Order> findbyId(long id){
       return orderRepository.findById(id);
    }



}
