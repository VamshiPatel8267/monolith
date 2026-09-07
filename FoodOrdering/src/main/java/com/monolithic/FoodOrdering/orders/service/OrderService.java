package com.monolithic.FoodOrdering.orders.service;

import com.monolithic.FoodOrdering.notification.OrderNotification;
import com.monolithic.FoodOrdering.orders.model.Order;
import com.monolithic.FoodOrdering.orders.model.OrderStatus;
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

    @Autowired
    private OrderNotification orderNotification;

    public Order placeOrder(Order order) {

        Long userID = order.getUser().getId(); // getting userId from order
        User user_details = userRepository.findById(userID).orElseThrow(()->new RuntimeException("User Not Found"));
        order.setUser(user_details);

        orderNotification.sendOrderEmail(user_details.getEmail(),"Regarding Your Order "+ order.getOrderName(), "Hi"+ user_details.getName()+ "\n This email is regarding your order "+order.getOrderName() + " and currently it is "+ order.getStatus());

        return orderRepository.save(order);
    }

    public Order updateStatus(long id , OrderStatus status){

        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        String email = order.getUser().getEmail();
        orderNotification.sendOrderEmail(email , "Regarding Your Order "+order.getOrderName(), "Hi "+ order.getUser().getName()+" \n\n Your order number "+ order.getId()+ " is"+ order.getStatus()+ "\n\n Please pay "+ order.getPrice()+ "and collect your order");
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

    public List<Order> getOrdersById(long id){
        User user = userRepository.findById(id).orElseThrow();
        return user.getOrders();
    }


}
