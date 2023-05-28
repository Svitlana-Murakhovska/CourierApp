package kafka;

import Cuirer.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderStatusListener {

        private final Map<Long, Order> orderMap;

        public OrderStatusListener(Map<Long, Order> orderMap) {
            this.orderMap = orderMap;
        }

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void listen(Order updatedOrder) {
        // Обновление статуса заказа в базе данных
        Order existingOrder = orderMap.get(updatedOrder.getId());
        if (existingOrder != null) {
            existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
        }
    }
}
