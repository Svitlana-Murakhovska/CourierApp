package Curier.kafka;

import client.model.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderStatusListener {

        private final Map<Long, Notification> orderMap;

        public OrderStatusListener(Map<Long, Notification> orderMap) {
            this.orderMap = orderMap;
        }

    @KafkaListener(topics = "order-topic", groupId = "couirer-group")
    public void listen(Notification updatedOrder) {
        // Обновление статуса заказа в базе данных
        Notification existingOrder = orderMap.get(updatedOrder.getId());
        if (existingOrder != null) {
            existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
        }
    }
}
