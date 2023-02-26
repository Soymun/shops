package com.example.shop.Entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String about;

    private Double weight;

    private TypeOfWeight typeOfWeight;

    @Column(name = "type_of_food_id")
    private Long typeOfFoodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_of_food_id", insertable = false, updatable = false)
    private TypeOfFood typeOfFood;

    private Double calories;

    private Long price;

    private String urlToPngFile;

    private Boolean inBallsProgram;

    private Long ballsPrice;
}
