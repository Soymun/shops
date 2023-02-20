package com.example.shop.DTO.Product;

import com.example.shop.DTO.TypeOfFood.TypeOfFoodDto;
import com.example.shop.Entity.Comment;
import com.example.shop.Entity.TypeOfFood;
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

    private TypeOfFoodDto typeOfFoodDto;

    private Double calories;

    private Long price;

    private String urlToPngFile;

    private boolean inBallsProgram;

    private Long ballsPrice;

    public ProductDTO(Long id, String name, String about, Double weight, TypeOfFood typeOfFood, TypeOfWeight typeOfWeight, Double calories, Long price, String urlToPngFile, boolean inBallsProgram, Long ballsPrice) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.weight = weight;
        this.typeOfWeight = typeOfWeight;
        this.typeOfFoodDto = new TypeOfFoodDto(typeOfFood.getId(), typeOfFood.getName(), typeOfFood.getUrlToPhoto(), typeOfFood.getTypeOfFoodId());
        this.calories = calories;
        this.price = price;
        this.urlToPngFile = urlToPngFile;
        this.inBallsProgram = inBallsProgram;
        this.ballsPrice = ballsPrice;
    }
}
