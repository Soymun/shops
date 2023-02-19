package com.example.shop.Controllers;


import com.example.shop.DTO.Comment.CommentCreateDto;
import com.example.shop.DTO.Comment.CommentUpdateDto;
import com.example.shop.Response.ResponseDto;
import com.example.shop.Service.Imp.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping("/comment")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDto commentCreateDto){
        commentService.createComment(commentCreateDto);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/comment/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        commentService.deleteCommentById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/comment/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getCommentById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(commentService.getCommentById(id)).build());
    }

    @GetMapping("/product/comment/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getCommentByProductId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(commentService.getCommentByProductId(id)).build());
    }

    @GetMapping("/user/comment/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getCommentByUserId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(commentService.getCommentByUserId(id)).build());
    }

    @PatchMapping("/comment")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> patchComment(@RequestBody CommentUpdateDto commentUpdateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(commentService.updateComment(commentUpdateDto)).build());
    }
}
