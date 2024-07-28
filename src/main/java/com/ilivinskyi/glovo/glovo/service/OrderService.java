package com.ilivinskyi.glovo.glovo.service;

import com.ilivinskyi.glovo.glovo.models.CustomerOrder;
import com.ilivinskyi.glovo.glovo.models.Product;
import com.ilivinskyi.glovo.glovo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public CustomerOrder getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public CustomerOrder addOrder(CustomerOrder customerOrder) {
        return orderRepository.save(customerOrder);
    }

    public CustomerOrder updateOrder(Long id, CustomerOrder customerOrder) {
        if (orderRepository.existsById(id)) {
            customerOrder.setId(id);
            return orderRepository.save(customerOrder);
        }
        return null;
    }

    public CustomerOrder addProductToOrder(Long id, Product product) {
        Optional<CustomerOrder> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            CustomerOrder customerOrder = optionalOrder.get();
            customerOrder.getProducts().add(product);
            return orderRepository.save(customerOrder);
        }
        return null;
    }

    public CustomerOrder removeProductFromOrder(Long id, Long productId) {
        Optional<CustomerOrder> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            CustomerOrder customerOrder = optionalOrder.get();
            customerOrder.getProducts().removeIf(product -> product.getId().equals(productId));
            return orderRepository.save(customerOrder);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
