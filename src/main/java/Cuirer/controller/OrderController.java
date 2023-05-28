package Cuirer.controller;

import Cuirer.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class OrderController {

    private final KafkaTemplate<Long, Order> kafkaTemplate;
    private final Map<Long, Order> orderMap;

    @Autowired
    public OrderController(KafkaTemplate<Long, Order> kafkaTemplate, Map<Long, Order> orderMap) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderMap = orderMap;
    }

    @PostMapping("/api/orders")
    public ResponseEntity<String> receiveOrder(@RequestBody Order order) {
        // Save the order in the map
        orderMap.put(order.getId(), order);


        // Publish the order to Kafka
       kafkaTemplate.send("order-topic", order);
        return ResponseEntity.ok("Success");
        ///have issue
    }

    @GetMapping("/{orderId}/status")
    public String getOrderStatus(@PathVariable Long orderId) {
        Order order= orderMap.get(orderId);
        if (order != null) {
            return order.getOrderStatus();
        } else {
          return "Order not found";
        }
    }
}
///kafka template  - описати, розібратись (чому не працює)
// перенести все в клієнт фолдер ++