package com.example.shop.Entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "basket_id")
    private List<Product> productList;
}
