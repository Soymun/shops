package com.example.shop.Entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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

    private TypeOfFood typeOfFood;

    private Double calories;

    private Long price;

    private String urlToPngFile;

    private boolean inBallsProgram;

    private Long ballsPrice;
}
