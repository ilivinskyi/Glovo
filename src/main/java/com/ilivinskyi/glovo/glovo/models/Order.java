package com.ilivinskyi.glovo.glovo.models;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {
    private Long id;
    private List<Product> products;
    private Date date;
    private String customer;
    private String status;
}
