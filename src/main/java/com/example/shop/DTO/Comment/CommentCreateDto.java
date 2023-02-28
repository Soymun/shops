package com.example.shop.DTO.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentCreateDto {

    private Long productId;

    private Long userId;

    private String comment;

    private Double rating;
}
