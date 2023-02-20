package com.example.shop.Facade;

import com.example.shop.DTO.Comment.CommentCreateDto;
import com.example.shop.DTO.Comment.CommentDTO;
import com.example.shop.DTO.Comment.CommentUpdateDto;
import com.example.shop.Service.Imp.CommentServiceImpl;
import com.example.shop.Service.Imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentFacade {

    private final CommentServiceImpl commentService;

    private final UserServiceImp userServiceImp;

    public void createComment(CommentCreateDto commentCreateDto){
        commentService.createComment(commentCreateDto);
    }

    public void deleteCommentById(Long id){
        commentService.deleteCommentById(id);
    }

    public CommentDTO getCommentById(Long id){
        CommentDTO commentDTO = commentService.getCommentById(id);
        commentDTO.setUserDto(userServiceImp.getUserById(commentDTO.getUserDto().getId()));
        return commentDTO;
    }

    public List<CommentDTO> getCommentByProductId(Long id){
        return commentService.getCommentByProductId(id).stream().peek(n -> n.setUserDto(userServiceImp.getUserById(n.getUserDto().getId()))).toList();
    }

    public List<CommentDTO> getCommentByUserId(Long id){
        return commentService.getCommentByUserId(id).stream().peek(n -> n.setUserDto(userServiceImp.getUserById(n.getUserDto().getId()))).toList();
    }

    public CommentDTO updateComment(CommentUpdateDto commentUpdateDto){
        return commentService.updateComment(commentUpdateDto);
    }
}
