package com.example.shop.DTO;


import lombok.Data;

@Data
public class ProductListDTO {

    private String name;

    private boolean ADName;

    private Long price;

    private boolean ADPrice;

    private Long likes;

    private boolean ADLikes;
}
