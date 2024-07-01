package com.ilivinskyi.glovo.glovo.service;

import com.ilivinskyi.glovo.glovo.models.Product;
import org.springframework.stereotype.Service;
import com.ilivinskyi.glovo.glovo.models.Order;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
    private Map<Long, Order> orders = new HashMap<>();
    private Long currentId = 1L;

    public Order getOrder(Long id) {
        return orders.get(id);
    }

    public Order addOrder(Order order) {
        order.setId(currentId++);
        orders.put(order.getId(), order);
        return order;
    }

    public Order updateOrder(Long id, Order order) {
        orders.put(id, order);
        return order;
    }

    public Order addProductToOrder(Long orderId, Product product) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.getProducts().add(product);
        }
        return order;
    }

    public Order removeProductFromOrder(Long orderId, Long productId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.getProducts().removeIf(product -> product.getId().equals(productId));
        }
        return order;
    }

    public void deleteOrder(Long id) {
        orders.remove(id);
    }
}
