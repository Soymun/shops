package com.example.shop.Service;

import com.example.shop.DTO.Comment.CommentCreateDto;
import com.example.shop.DTO.Comment.CommentDTO;
import com.example.shop.DTO.Comment.CommentUpdateDto;

import java.util.List;

public interface CommentService {


    void createComment(CommentCreateDto commentCreateDto);

    void deleteCommentById(Long id);

    CommentDTO getCommentById(Long id);

    List<CommentDTO> getCommentByProductId(Long id);

    List<CommentDTO> getCommentByUserId(Long id);

    CommentDTO updateComment(CommentUpdateDto commentUpdateDto);
}
