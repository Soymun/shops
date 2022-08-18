package com.example.shop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetDTO {

    private Long id;

    private String name;

    private Long price;

    private String urlToPngFile;

    private Long likes;
}
