package com.monolithic.FoodOrdering.orders.service;

import com.monolithic.FoodOrdering.orders.model.Order;
import com.monolithic.FoodOrdering.orders.model.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void SendMessage(OrderCreatedEvent event){
        kafkaTemplate.send("orders", event);
    }
}
