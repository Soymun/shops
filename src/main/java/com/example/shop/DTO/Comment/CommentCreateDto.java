package com.example.shop.DTO.Comment;

import lombok.Data;

@Data
public class CommentCreateDto {

    private Long productId;

    private Long userId;

    private String comment;

    private Double rating;
}
