package com.monolithic.FoodOrdering.notification;

import com.monolithic.FoodOrdering.orders.model.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendOrderEmail(OrderCreatedEvent event){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(event.getEmail());
        mailMessage.setSubject(
                String.format("Your Order %s with Order id %d is placed", event.getOrderName(), event.getId())
        );
        mailMessage.setText(String.format("Hi %s \nYour Order %s with Order id %d is placed", event.getName() , event.getOrderName(), event.getId()));
        mailSender.send(mailMessage);
    }

}
