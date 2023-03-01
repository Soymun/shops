package com.example.shop.DTO.Comment;

import com.example.shop.DTO.User.UserGetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentDTO {

    private Long id;

    private Long productId;

    private UserGetDto user;

    private LocalDateTime localDateTime;

    private String comment;

    private Double rating;
}
