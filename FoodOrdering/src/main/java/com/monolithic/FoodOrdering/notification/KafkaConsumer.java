package com.monolithic.FoodOrdering.notification;


import com.monolithic.FoodOrdering.orders.model.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "orders-placed", groupId = "orders-groups")
    public void sendMessage(OrderEvent event){

        SimpleMailMessage sendMail = new SimpleMailMessage();
        sendMail.setTo(event.getEmail());
        sendMail.setSubject(String.format("Your Order %s with Order id %d is confirmed", event.getOrderName(), event.getId()));
        sendMail.setText(String.format("Hi %s \nYour Order %s with Order id %d is confirmed", event.getName() , event.getOrderName(), event.getId()));
        mailSender.send(sendMail);
    }
}
