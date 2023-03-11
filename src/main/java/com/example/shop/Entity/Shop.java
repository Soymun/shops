package com.example.shop.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String street;

    private String house;

    @Column(name = "number_house")
    private String numberHouse;

    @Column(name = "cord_x")
    private Double cordX;

    @Column(name = "cord_y")
    private Double cordY;

    private LocalTime open;

    private LocalTime close;

    private String phone;
}
