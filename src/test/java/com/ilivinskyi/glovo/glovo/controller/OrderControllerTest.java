package com.ilivinskyi.glovo.glovo.controller;

import com.ilivinskyi.glovo.glovo.models.Order;
import com.ilivinskyi.glovo.glovo.models.Product;
import com.ilivinskyi.glovo.glovo.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    Order order;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
    }

    @Test
    void getOrderTest() {
        when(orderService.getOrder(1L)).thenReturn(order);

        var response = orderController.getOrder(1L);

        assertEquals(ResponseEntity.ok(order), response);
        verify(orderService, times(1)).getOrder(1L);
    }

    @Test
    void addOrderTest() {
        when(orderService.addOrder(order)).thenReturn(order);

        var response = orderController.addOrder(order);

        assertEquals(ResponseEntity.ok(order), response);
        verify(orderService, times(1)).addOrder(order);
    }

    @Test
    void updateOrderTest() {
        when(orderService.updateOrder(1L, order)).thenReturn(order);

        var response = orderController.updateOrder(1L, order);

        assertEquals(ResponseEntity.ok(order), response);
        verify(orderService, times(1)).updateOrder(1L, order);
    }

    @Test
    void addProductToOrderTest() {
        Product product = new Product();
        when(orderService.addProductToOrder(1L, product)).thenReturn(order);

        var response = orderController.addProductToOrder(1L, product);

        assertEquals(ResponseEntity.ok(order), response);
        verify(orderService, times(1)).addProductToOrder(1L, product);
    }

    @Test
    void removeProductFromOrderTest() {
        when(orderService.removeProductFromOrder(1L, 1L)).thenReturn(order);

        ResponseEntity<Order> response = orderController.removeProductFromOrder(1L, 1L);

        assertEquals(ResponseEntity.ok(order), response);
        verify(orderService, times(1)).removeProductFromOrder(1L, 1L);
    }

    @Test
    void deleteOrderTest() {
        doNothing().when(orderService).deleteOrder(1L);

        ResponseEntity<Void> response = orderController.deleteOrder(1L);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(orderService, times(1)).deleteOrder(1L);
    }
}