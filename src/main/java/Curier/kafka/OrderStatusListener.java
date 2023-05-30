package Curier.kafka;

import client.model.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderStatusListener {

    private final KafkaTemplate<Long, Notification> kafkaTemplate;

    public OrderStatusListener(KafkaTemplate<Long, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @KafkaListener(topics = "order-topic", groupId = "cuirer-group")
    public void listen(Notification updatedOrder) {
        Notification message = new Notification(updatedOrder.getId(), "Ready" );
        kafkaTemplate.send("notification-topic", message);
    }
}
