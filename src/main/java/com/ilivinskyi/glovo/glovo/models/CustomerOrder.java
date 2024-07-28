package com.ilivinskyi.glovo.glovo.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
    private Date date;
    private String customer;
    private String status;
}
