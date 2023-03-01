package com.example.shop.Service.Imp;

import com.example.shop.DTO.Comment.CommentCreateDto;
import com.example.shop.DTO.Comment.CommentDTO;
import com.example.shop.DTO.Comment.CommentUpdateDto;
import com.example.shop.Entity.Comment;
import com.example.shop.Exception.NoAccessOperation;
import com.example.shop.Exception.NoFoundException;
import com.example.shop.DTO.Security.UserPrincipalData;
import com.example.shop.Mappers.CommentMapper;
import com.example.shop.Repository.CommentRepository;
import com.example.shop.Service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final UserPrincipalData userPrincipalData;

    @Override
    public void createComment(CommentCreateDto commentCreateDto) {
        log.info("Сохранение коментария");
        Comment comment = commentMapper.commentCreateDtoToComment(commentCreateDto);
        comment.setLocalDateTime(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long id) {
        if(!Objects.equals(id, userPrincipalData.getId())){
            throw new NoAccessOperation("Операция обновления запрещена");
        }
        log.info("Удаление коментария с id {}", id);
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDTO getCommentById(Long id) {
        log.info("Выдача коментария по id {}", id);
        return commentMapper
                .commentToCommentDto(commentRepository
                        .findById(id)
                        .orElseThrow(() -> {
                            throw new NoFoundException("Коментарий не найден");
                        }));
    }

    @Override
    public List<CommentDTO> getCommentByProductId(Long id) {
        log.info("Выдача коментарий по продукту с id {}", id);
        return commentRepository.getCommentsByProductIdOrderByLocalDateTime(id)
                .stream()
                .map(commentMapper::commentToCommentDto)
                .toList();
    }

    @Override
    public List<CommentDTO> getCommentByUserId(Long id) {
        log.info("Выдача коментариев по пользевателю с id {}", id);
        return commentRepository.getCommentsByUserIdOrderByLocalDateTime(id)
                .stream()
                .map(commentMapper::commentToCommentDto)
                .toList();
    }

    @Override
    public CommentDTO updateComment(CommentUpdateDto commentUpdateDto) {
        log.info("Изменеие коментария");
        Comment comment = commentRepository.findById(commentUpdateDto.getId()).orElseThrow(() -> {
            throw new NoFoundException("Коментарий не найден");
        });
        if(!Objects.equals(comment.getUserId(), userPrincipalData.getId())){
            throw new NoAccessOperation("Операция обновления запрещена");
        }
        if (commentUpdateDto.getComment() != null) {
            comment.setComment(commentUpdateDto.getComment());
        }
        if (commentUpdateDto.getRating() != null) {
            comment.setRating(commentUpdateDto.getRating());
        }
        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }
}
