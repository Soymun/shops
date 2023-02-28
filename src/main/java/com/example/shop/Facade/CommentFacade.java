package com.example.shop.Facade;

import com.example.shop.DTO.Comment.CommentCreateDto;
import com.example.shop.DTO.Comment.CommentDTO;
import com.example.shop.DTO.Comment.CommentUpdateDto;
import com.example.shop.Service.Imp.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentFacade {

    private final CommentServiceImpl commentService;

    public void createComment(CommentCreateDto commentCreateDto){
        commentService.createComment(commentCreateDto);
    }

    public void deleteCommentById(Long id){
        commentService.deleteCommentById(id);
    }

    public CommentDTO getCommentById(Long id){
        return commentService.getCommentById(id);
    }

    public List<CommentDTO> getCommentByProductId(Long id){
        return commentService.getCommentByProductId(id);
    }

    public List<CommentDTO> getCommentByUserId(Long id){
        return commentService.getCommentByUserId(id);
    }

    public CommentDTO updateComment(CommentUpdateDto commentUpdateDto){
        return commentService.updateComment(commentUpdateDto);
    }
}
