package com.example.shop.DTO.Product;

import com.example.shop.Entity.Comment;
import com.example.shop.Entity.TypeOfWeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String about;

    private Double weight;

    private TypeOfWeight typeOfWeight;

    private Long typeOfFoodId;

    private Double calories;

    private Long price;

    private String urlToPngFile;

    private boolean inBallsProgram;

    private Long ballsPrice;

}
