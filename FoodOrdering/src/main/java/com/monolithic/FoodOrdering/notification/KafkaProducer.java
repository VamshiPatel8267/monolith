package com.monolithic.FoodOrdering.notification;

import com.monolithic.FoodOrdering.orders.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void receive(OrderEvent event){
        kafkaTemplate.send("orders-placed", event);
    }
}
