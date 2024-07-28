package com.ilivinskyi.glovo.glovo.controller;

import com.ilivinskyi.glovo.glovo.models.CustomerOrder;
import com.ilivinskyi.glovo.glovo.models.Product;
import com.ilivinskyi.glovo.glovo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrder> getOrder(@PathVariable Long id) {
        CustomerOrder customerOrder = orderService.getOrder(id);
        if (customerOrder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerOrder);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerOrder>> getOrders() {
        List<CustomerOrder> customerOrder = orderService.getAllOrders();
        if (customerOrder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerOrder);
    }

    @PostMapping
    public ResponseEntity<CustomerOrder> addOrder(@RequestBody CustomerOrder customerOrder) {
        CustomerOrder newCustomerOrder = orderService.addOrder(customerOrder);
        return ResponseEntity.ok(newCustomerOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerOrder> updateOrder(@PathVariable Long id, @RequestBody CustomerOrder customerOrder) {
        CustomerOrder updatedCustomerOrder = orderService.updateOrder(id, customerOrder);
        return ResponseEntity.ok(updatedCustomerOrder);
    }

    @PatchMapping("/{id}/products")
    public ResponseEntity<CustomerOrder> addProductToOrder(@PathVariable Long id, @RequestBody Product product) {
        CustomerOrder updatedCustomerOrder = orderService.addProductToOrder(id, product);
        if (updatedCustomerOrder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCustomerOrder);
    }

    @DeleteMapping("/{id}/products/{productId}")
    public ResponseEntity<CustomerOrder> removeProductFromOrder(@PathVariable Long id, @PathVariable Long productId) {
        CustomerOrder updatedCustomerOrder = orderService.removeProductFromOrder(id, productId);
        if (updatedCustomerOrder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCustomerOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
