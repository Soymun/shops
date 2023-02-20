package com.example.shop.DTO.Comment;

import com.example.shop.DTO.User.UserDto;
import com.example.shop.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;

    private Long productId;

    private UserDto userDto;

    private LocalDateTime localDateTime;

    private String comment;

    private Double rating;

    public CommentDTO(Long id, Long productId, User user, LocalDateTime localDateTime, String comment, Double rating) {
        this.id = id;
        this.productId = productId;
        this.userDto = new UserDto(user.getId(), user.getEmail(), user.getBalls(), user.getUsername(), user.getRole(), user.getRating());
        this.localDateTime = localDateTime;
        this.comment = comment;
        this.rating = rating;
    }
}
