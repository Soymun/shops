package com.example.shop.DTO.Comment;

import com.example.shop.DTO.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentDTO {

    private Long id;

    private Long productId;

    private UserDto user;

    private LocalDateTime localDateTime;

    private String comment;

    private Double rating;
}
