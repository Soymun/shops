package com.example.shop.Controllers;


import com.example.shop.DTO.Comment.CommentCreateDto;
import com.example.shop.DTO.Comment.CommentUpdateDto;
import com.example.shop.Facade.CommentFacade;
import com.example.shop.Response.ResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentService;

    @ApiOperation(value = "Метод для создания коментария.", notes = "Могут пользоваться все.Возвращает код 201")
    @PostMapping("/comment")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDto commentCreateDto){
        commentService.createComment(commentCreateDto);
        return ResponseEntity.status(201).build();
    }

    @ApiOperation(value = "Метод для удаления.", notes = "Могут пользоваться все.Возвращает код 204")
    @DeleteMapping("/comment/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        commentService.deleteCommentById(id);
        return ResponseEntity.status(204).build();
    }

    @ApiOperation(value = "Метод для получения одного коментария.", notes = "Могут пользоваться все.Возвращает CommentDTO")
    @GetMapping("/comment/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getCommentById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(commentService.getCommentById(id)).build());
    }

    @ApiOperation(value = "Метод для получения листа коментариев по product id.", notes = "Могут пользоваться все.Возвращает list CommentDTO")
    @GetMapping("/product/comment/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getCommentByProductId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(commentService.getCommentByProductId(id)).build());
    }

    @ApiOperation(value = "Метод для получения листа коментариев по user id.", notes = "Могут пользоваться все.Возвращает list CommentDTO")
    @GetMapping("/user/comment/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getCommentByUserId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(commentService.getCommentByUserId(id)).build());
    }

    @ApiOperation(value = "Изменение коментария.", notes = "Могут пользоваться все.Возвращает list CommentDTO")
    @PatchMapping("/comment")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> patchComment(@RequestBody CommentUpdateDto commentUpdateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(commentService.updateComment(commentUpdateDto)).build());
    }
}
