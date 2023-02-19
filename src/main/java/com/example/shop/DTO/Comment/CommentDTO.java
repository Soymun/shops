package com.example.shop.DTO.Comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private Long id;

    private Long productId;

    private Long userId;

    private LocalDateTime localDateTime;

    private String comment;

    private Double rating;
}
