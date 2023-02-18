package com.example.shop.Mappers;

import com.example.shop.DTO.Comment.CommentCreateDto;
import com.example.shop.DTO.Comment.CommentDTO;
import com.example.shop.Entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentCreateDtoToComment(CommentCreateDto commentCreateDto);

    CommentDTO commentToCommentDto(Comment comment);
}
