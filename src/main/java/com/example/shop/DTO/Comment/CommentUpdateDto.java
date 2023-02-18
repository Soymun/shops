package com.example.shop.DTO.Comment;

import lombok.Data;

@Data
public class CommentUpdateDto {

    private Long id;

    private String comment;

    private Double rating;
}
