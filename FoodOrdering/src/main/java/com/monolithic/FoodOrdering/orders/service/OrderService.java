package com.monolithic.FoodOrdering.orders.service;

import com.monolithic.FoodOrdering.notification.EmailService;
import com.monolithic.FoodOrdering.orders.model.Order;
import com.monolithic.FoodOrdering.orders.model.OrderCreatedEvent;
import com.monolithic.FoodOrdering.orders.model.OrderStatus;
import com.monolithic.FoodOrdering.orders.repository.OrderRepository;
import com.monolithic.FoodOrdering.user.model.User;
import com.monolithic.FoodOrdering.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private KafkaProducer kafkaProducer;

    private OrderCreatedEvent orderCreatedEvent;

    public Order placeOrder(Order order) {

        long userID = order.getUser().getId();
        User user_details = userRepository.findById(userID).orElseThrow(()->new RuntimeException("User Not Found"));
        order.setUser(user_details); // mapping userdetails to user which is in order else we get null value
        // now the user details will be in order json and will be able to use them in orderEvent as we are passing only order details and userid in body of orderplaced
        Order savedOrder = orderRepository.save(order);

        //mapping all the details with orderEvent
        OrderCreatedEvent orderEvent = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getUser().getEmail(),
                savedOrder.getUser().getName(),
                savedOrder.getOrderName(),
                savedOrder.getPrice()
        );
        kafkaProducer.SendMessage(orderEvent);
        return savedOrder;
    }

//    public Order updateStatus(long id , OrderStatus status){
//
//        Order order = orderRepository.findById(id).orElseThrow();
//        order.setStatus(status);
//        String email = order.getUser().getEmail();
//        emailService.sendOrderEmail(email , "Regarding Your Order "+order.getOrderName(), "Hi "+ order.getUser().getName()+" \n\n Your order number "+ order.getId()+ " is"+ order.getStatus()+ "\n\n Please pay "+ order.getPrice()+ "and collect your order");
//        return orderRepository.save(order);
//
//    }

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
