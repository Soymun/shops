package com.example.shop.DTO.Product;

import com.example.shop.Entity.Comment;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
public class ProductDTO {

    private Long userId;

    private String name;

    private String about;

    private Long price;
}
