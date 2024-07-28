package com.ilivinskyi.glovo.glovo.TestData;

import com.ilivinskyi.glovo.glovo.models.CustomerOrder;
import com.ilivinskyi.glovo.glovo.models.Product;

import java.util.Collections;
import java.util.Date;

public class DefaultOrderData {
    public static CustomerOrder getDefaultOrder() {
        var product = getDefaultProduct();

        var order = new CustomerOrder();
        order.setCustomer("John Doe");
        order.setDate(new Date());
        order.setStatus("Pending");
        order.setProducts(Collections.singletonList(product));

        return order;
    }

    public static Product getDefaultProduct() {
        var product = new Product();
        product.setName("Product1");
        product.setPrice(10.0);

        return product;
    }

    public static String getOrderJsonString() {
        return "{\"customer\":\"Jane Doe\",\"date\":\"2023-07-02T02:00:00.000+00:00\",\"status\":\"Shipped\",\"products\":[{\"name\":\"Product1\",\"price\":10.0}]}";
    }
}
