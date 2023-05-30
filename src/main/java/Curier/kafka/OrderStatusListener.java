package Curier.kafka;

import client.model.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusListener {

    private final KafkaTemplate<Long, Notification> kafkaTemplate;

    public OrderStatusListener(KafkaTemplate<Long, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @KafkaListener(topics = "notification-topic", groupId = "cuirer-group")
    public void listen(Notification updatedOrder) throws InterruptedException {
        Thread.sleep(5000);
        if (!"Delivered".equals(updatedOrder.getOrderStatus())){
                Notification message = new Notification(updatedOrder.getId(), "Delivered" );
                kafkaTemplate.send("notification-topic", message);
        }
    }
}
