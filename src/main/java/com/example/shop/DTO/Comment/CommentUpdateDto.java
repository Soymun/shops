package com.example.shop.DTO.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentUpdateDto {

    private Long id;

    private String comment;

    private Double rating;
}
