package com.example.shop.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductSortedDTO {

    private String name;

    private Long fromprice;

    private Long toprice;

    private Long fromlikes;

    private Long tolikes;

}
