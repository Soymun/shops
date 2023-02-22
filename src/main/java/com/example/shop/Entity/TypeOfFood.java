package com.example.shop.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "type_of_food")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TypeOfFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "url_to_photo")
    private String urlToPhoto;

    @Column(name = "type_of_food_id")
    private Long typeOfFoodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_of_food_id", insertable = false, updatable = false)
    private TypeOfFood typeOfFood;

}
