package com.monolithic.FoodOrdering.notification;

import com.monolithic.FoodOrdering.orders.model.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "orders", groupId = "orders-group")
    public void receiver(OrderCreatedEvent event) {

        emailService.sendOrderEmail(event);
    }
}