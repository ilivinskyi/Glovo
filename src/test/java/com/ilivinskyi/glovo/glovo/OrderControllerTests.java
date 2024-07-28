package com.ilivinskyi.glovo.glovo;

import com.ilivinskyi.glovo.glovo.repository.OrderRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.ilivinskyi.glovo.glovo.TestData.DefaultOrderData.getDefaultOrder;
import static com.ilivinskyi.glovo.glovo.TestData.DefaultOrderData.getOrderJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-local.properties")
class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testGetOrder() throws Exception {
        var order = getDefaultOrder();
        orderRepository.save(order);
        mockMvc.perform(get("/orders/" + order.getId()))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void testGetOrders() {
        var order1 = getDefaultOrder();
        var order2 = getDefaultOrder();
        orderRepository.save(order1);
        orderRepository.save(order2);

        mockMvc.perform(get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void testAddOrder() {
        var order = getOrderJsonString();
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(order))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void testUpdateOrder() {
        var order = getDefaultOrder();
        var savedOrder = orderRepository.save(order);
        var orderId = savedOrder.getId();
        var updateOrderJson = getOrderJsonString();
        mockMvc.perform(put("/orders/" + orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateOrderJson))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void testAddProductToOrder() {
        var order = getDefaultOrder();
        var savedOrder = orderRepository.save(order);
        Long orderId = savedOrder.getId();
        String productJson = "{\"name\":\"Product2\",\"price\":20.0}";
        mockMvc.perform(patch("/orders/" + orderId + "/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void testRemoveProductFromOrder() {
        var order = getDefaultOrder();
        var savedOrder = orderRepository.save(order);
        var orderId = savedOrder.getId();
        var productId = savedOrder.getProducts().getFirst().getId();
        mockMvc.perform(delete("/orders/" + orderId + "/products/" + productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void testDeleteOrder() {
        var order = getDefaultOrder();
        var savedOrder = orderRepository.save(order);
        var orderId = savedOrder.getId();
        mockMvc.perform(delete("/orders/" + orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}