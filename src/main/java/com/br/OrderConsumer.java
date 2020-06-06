package com.br;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.util.Map;
 
@Component
public class OrderConsumer {
 
    @RabbitListener(queues = {"${queue.order.name}"})
    public void receive(@Payload String order) {
        System.out.println("Order: " + order);
    }
}
